<template>
  <h1>Seeking mental health assistance</h1>
  <p>
    Please see the following links and contacts for mental health support if you
    have been affected by anything discussed in the information sheet or
    workshops.
  </p>

  <h3>How to access mental health services</h3>
  <a
    href="https://www.nhs.uk/using-the-nhs/nhs-services/mental-health-services/how-to-access-mental-health-services/"
    >https://www.nhs.uk/using-the-nhs/nhs-services/mental-health-services/how-to-access-mental-health-services/</a
  >
  <h3>Mental health support services near you</h3>
  <a
    href="https://www.nhs.uk/service-search/other-services/Mental-health-information-and-support/LocationSearch/330"
    >https://www.nhs.uk/service-search/other-services/Mental-health-information-and-support/LocationSearch/330</a
  >

  <HorizontalLine />

  <h3>Free listening services</h3>
  <h4>Samaritans:</h4>
  <p>
    <strong>Call:</strong> 116 123<br />
    <strong>Email: </strong
    ><a href="mailto:jo@samaritans.org">jo@samaritans.org</a> for a reply in 24
    hours
  </p>
  <h4>Shout crisis text link:</h4>
  <p>Text “SHOUT” to 85258</p>
  <HorizontalLine />

  <h3>Online resources</h3>
  <h4>Mind online offers information on ways to help yourself in a crisis</h4>
  <a
    href="https://www.mind.org.uk/need-urgent-help/what-can-i-do-to-help-myself-cope/"
    >https://www.mind.org.uk/need-urgent-help/what-can-i-do-to-help-myself-cope/</a
  >

  <h4>or how to put in support for a potential mental health crisis</h4>
  <a
    href="https://www.mind.org.uk/information-support/guides-to-support-and-services/crisis-services/planning-for-a-crisis/"
    >https://www.mind.org.uk/information-support/guides-to-support-and-services/crisis-services/planning-for-a-crisis/</a
  >

  <h4>Resources for people who have experienced childhood trauma:</h4>

  <p>
    <a href="https://www.thesurvivorstrust.org/"
      >https://www.thesurvivorstrust.org/</a
    ><br />
    <strong>Tel:</strong> 08088 010818
  </p>
  <p>
    <a href="https://napac.org.uk/">https://napac.org.uk/</a><br />
    <strong>Tel:</strong> 08088 010331
  </p>
</template>
<script>
import HorizontalLine from '@/components/HorizontalLine';

export default {
  name: 'Help',
  components: {
    HorizontalLine,
  },
};
</script>
<style scoped>
h4 {
  font-size: 18px;
  margin-bottom: 0.5em;
}
</style>
