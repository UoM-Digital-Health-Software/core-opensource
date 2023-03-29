<template>
  <div class="button" :class="{ grey: color === 'grey' }" role="button">
    <slot></slot>
    <img v-if="icon === 'arrow'" src="../assets/arrow-right.svg" alt='' />
    <img v-else-if="icon === 'exit'" src="../assets/exit.svg" alt='' />
    <img v-else-if="icon === 'download'" src="../assets/download.svg" alt='' />
  </div>
</template>

<script>
export default {
  name: 'Button',
  props: ['icon', 'color'],
};
</script>

<style scoped>
.button {
  padding: 20px 30px;
  font-weight: bold;
  font-size: 18px;
  border-radius: 4px;
  display: inline-block;
  color: white;
  background: #1b3f75;
  margin: 20px 20px 20px 4px;
  user-select: none;
  cursor: pointer;
}

.grey {
  background: #767676;
}

.button img {
  height: 22px;
  margin: 0 0 -4px 8px;
}
</style>
