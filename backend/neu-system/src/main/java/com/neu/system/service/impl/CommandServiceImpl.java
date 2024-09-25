package com.neu.system.service.impl;
import com.neu.system.service.CommandService;
import org.springframework.beans.factory.InitializingBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@Service
public class CommandServiceImpl implements CommandService, InitializingBean {

    @Value("${cmd.threadname:cmd-executor}")
    private String threadName;

    @Value("${cmd.taskQueueMaxStorage:20}")
    private Integer taskQueueMaxStorage;

    @Value("${cmd.corePoolSize:4}")
    private Integer corePoolSize;

    @Value("${cmd.maximumPoolSize:8}")
    private Integer maximumPoolSize;

    @Value("${cmd.keepAliveSeconds:15}")
    private              Integer            keepAliveSeconds;
    private              ThreadPoolExecutor executor;
    private static final String             BASH       = "sh";
    private static final String             BASH_PARAM = "-c";

    @Override
    public String executeCommand(String cmd) {
        Process p = null;
        String res;
        try {
            List<String> cmds = new ArrayList<>();
            cmds.add(BASH);
            cmds.add(BASH_PARAM);
            cmds.add(cmd);
            ProcessBuilder pb = new ProcessBuilder(cmds);
            p = pb.start();

            Future<String> errorFuture = executor.submit(new ReadTask(p.getErrorStream()));
            Future<String> resFuture = executor.submit(new ReadTask(p.getInputStream()));
            int exitValue = p.waitFor();
            if (exitValue > 0) {
                throw new RuntimeException(errorFuture.get());
            }
            res = resFuture.get();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (p != null) {p.destroy();}
        }
        if (StringUtils.isNotBlank(res) && res.endsWith(System.lineSeparator())) {
            res = res.substring(0, res.lastIndexOf(System.lineSeparator()));
        }
        return res;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveSeconds, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(taskQueueMaxStorage),
                new ThreadFactory() {
                    public Thread newThread(Runnable r) {
                        return new Thread(r, threadName + r.hashCode());
                    }
                },
                new ThreadPoolExecutor.AbortPolicy());
    }
    class ReadTask implements Callable<String> {
        InputStream is;

        ReadTask(InputStream is) {
            this.is = is;
        }

        @Override
        public String call() throws Exception {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            return sb.toString();
        }
    }
}
