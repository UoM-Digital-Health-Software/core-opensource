<template>
  <Header></Header>
  <Page>
    <router-view />
  </Page>
</template>

<script>
import Header from '@/components/Header';
import Page from '@/components/Page';

export default {
  name: 'CORE',
  components: {
    Header,
    Page,
  },
};
</script>

<style>
html,
body {
  padding: 0;
  margin: 0;
  scrollbar-gutter: stable;
}
html,
body,
textarea {
  font-family: 'Helvetica Neue', Helvetica, Arial, sans-serif;
}
</style>
