<template>
  <div class="intro">
    <h1>{{ title }}</h1>
    <p v-html="context"></p>
  </div>
</template>

<script>
export default {
  props: ['title', 'context'],
};
</script>

<style scoped>
.intro {
  padding: 0 0 20px 10px;
}

h1 {
  margin: 0 0 25px 0;
  font-size: 28px;
  padding: 0;
  color: #1b3f75;
  font-weight: bold;
}

p {
  margin: 0;
  padding: 0;
  font-size: 20px;
  color: #767676;
}
</style>
