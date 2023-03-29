<template>
  <details ref="details">
    <summary>
      <div>{{ title }}</div>
    </summary>
    <div class="content"><slot /></div>
  </details>
</template>

<script>
export default {
  name: 'Collapsible',
  props: ['title'],
  methods: {
    collapse() {
      this.$refs.details.removeAttribute('open');
    },
  },
};
</script>

<style scoped>
details {
  width: 100%;
  border-top: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  position: relative;
  margin-top: -1px;
}

summary {
  list-style: none;
  color: #767676;
  background: white;
  padding: 1em;
  font-weight: bold;
}

.content {
  border-top: 1px solid #ccc;
  background: #f5f9ff;
  padding: 1em;
}

details summary div {
  display: inline-block;
  width: 95%;
  vertical-align: middle;
}

details summary::after {
  content: '\25B6';
  display: inline-block;
  text-align: right;
  width: 5%;
  vertical-align: middle;
}

details[open] summary::after {
  content: '\25BC';
}
details summary::-webkit-details-marker {   display:none; } 
details[open] summary {
  color: #234077;
}
</style>
