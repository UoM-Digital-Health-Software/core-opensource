<template>
  <header id="heading">
    <img
      id="logo"
      src="../assets/nihr.png"
      alt="National Institute for Health Research"
    />
    <div id="seeking" @click="seekingHelp()">
      <img src="../assets/help.svg" alt=''/>Seeking help
    </div>
  </header>
</template>
<script>
import router from '@/router';

export default {
  name: 'Header',
  methods: {
    seekingHelp() {
      router.push('help');
    },
  },
};
</script>
<style scoped>
#heading {
  border-bottom: 1px solid #767676;
  position: relative;
  margin-bottom: 4em;
}
#logo {
  width: 333px;
  max-width: 80%;
  padding: 20px 0 20px 40px;
}
#seeking {
  position: absolute;
  right: 0;
  top: 0;
  padding: 25px 40px 20px 0;
  font-size: 1.5em;
  color: #1b3f75;
  font-weight: bold;
  vertical-align: middle;
  cursor: pointer;
}
#seeking img {
  vertical-align: middle;
  width: 50px;
  padding-right: 5px;
}

@media screen and (max-width: 650px) {
  #heading {
    margin-bottom: 5em;
  }
  #seeking {
    text-align: right;
    padding: 8px 15px 0 0;
    font-size: 1.3em;
    top: 90px;
  }

  #seeking img {
    vertical-align: middle;
    width: 40px;
    padding-right: 5px;
  }
}
</style>
