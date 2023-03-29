<template>
  <video controls class="video">
    <source :src="require('../assets/videos/' + source)" type="video/mp4" />
    Sorry, your browser doesn't support embedded videos.

    <track
      label="English"
      kind="subtitles"
      srclang="en"
      :src="require('../assets/videos/' + subtitles)"
      default
    />

  </video>
</template>
<script>
export default {
  name: 'Video',
  props: ['source', 'description', 'subtitles'],
};
</script>
<style scoped>
.video {
  display: inline-block;
  width: 100%;
  margin-bottom: 30px;
}
</style>
