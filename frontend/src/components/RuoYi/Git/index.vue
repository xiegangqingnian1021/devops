<template>
  <div>
    <svg-icon icon-class="github" @click="goto"/>
  </div>
</template>

<script>
export default {
  name: 'NeuGit',
  data() {
    return {
      url: ''
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>