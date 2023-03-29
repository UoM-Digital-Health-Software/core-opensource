<template>
  <button><slot></slot></button>
</template>

<style scoped>
button {
  padding: 0.7em 1.2em;
  background: white;
  color: black;
  display: inline-block;
  border: none;
  border-radius: 0.2em;
  cursor: pointer;
  text-decoration: none;
  font-size: 0.9em;
  margin: 1em 0;
  border: 1px solid black;
}

button:disabled {
  background: #00856180;
  cursor: default;
}

button:hover {
  background: lightgrey;
}
</style>
