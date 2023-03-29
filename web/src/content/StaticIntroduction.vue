<template>
  <p>
    You are being invited to take part in a research study looking at whether it
    is possible to collect mental health information, e.g. answers to
    questionnaires, for people who are taking part in physical health research
    studies, so we can examine the relationship between physical and mental
    health.
  </p>
  <p>
    Before you decide whether to take part, it is important for you to
    understand why the research is being conducted and what it will involve. If
    you decide to participate, the questionnaires should take approximately 15
    minutes to complete.
  </p>
  <p>
    Please take time to read or listen to the following information carefully
    before deciding whether to take part.
  </p>
  <p>
    If you have any questions about the research, please contact the CMHDS
    research team on
    <a href="mailto:cmhds@manchester.ac.uk">cmhds@manchester.ac.uk</a>, rather
    than asking members of the physical health research study. This email
    address will be monitored until September 2022.
  </p>
  <p>
    If you have concerns about your wellbeing and want to seek help, please go
    to the seeking support tab in the top right-hand corner.
  </p>
  <p>Thank you for taking the time to learn about the project.</p>

  <Video
    source="intro_video.mp4"
    subtitles="intro_video_subtitles.vtt"
    description="National Institute for Health Research"
  ></Video>

  <Collapsible
    title="Who is conducting the research?"
    ref="SectionWho"
    @click="collapseOthers('SectionWho')"
    ><SectionWho
  /></Collapsible>

  <Collapsible
    title="What is the purpose of the research and why am I being asked to take part?"
    ref="SectionPurpose"
    @click="collapseOthers('SectionPurpose')"
    ><SectionPurpose
  /></Collapsible>

  <Collapsible
    title="The relationship between physical and mental health"
    ref="SectionRelationship"
    @click="collapseOthers('SectionRelationship')"
    ><SectionRelationship
  /></Collapsible>

  <Collapsible
    title="Benefits of collecting mental health and physical health data together"
    ref="SectionBenfits"
    @click="collapseOthers('SectionBenfits')"
    ><SectionBenfits
  /></Collapsible>

  <Collapsible
    title="Will I benefit from taking part in the research?"
    ref="SectionWillIBenefit"
    @click="collapseOthers('SectionWillIBenefit')"
    ><SectionWillIBenefit
  /></Collapsible>

  <Collapsible
    title="What will I have to do?"
    ref="SectionWhatIDo"
    @click="collapseOthers('SectionWhatIDo')"
    ><SectionWhatIDo
  /></Collapsible>

  <Collapsible
    title="What information will you collect about me and what will happen to the information?"
    ref="SectionWhatInformation"
    @click="collapseOthers('SectionWhatInformation')"
    ><SectionWhatInformation
  /></Collapsible>

  <Collapsible
    title="Under what legal basis are we collecting this information?"
    ref="SectionLegalBasis"
    @click="collapseOthers('SectionLegalBasis')"
    ><SectionLegalBasis
  /></Collapsible>

  <Collapsible
    title="What if I have a complaint?"
    ref="SectionComplaint"
    @click="collapseOthers('SectionComplaint')"
    ><SectionComplaint
  /></Collapsible>
</template>

<script>
import Collapsible from '@/components/Collapsible';
import Video from '@/components/Video';

import SectionWho from '@/content/SectionWho';
import SectionPurpose from '@/content/SectionPurpose';
import SectionRelationship from '@/content/SectionRelationship';
import SectionBenfits from '@/content/SectionBenfits';
import SectionWillIBenefit from '@/content/SectionWillIBenefit';
import SectionWhatIDo from '@/content/SectionWhatIDo';
import SectionWhatInformation from '@/content/SectionWhatInformation';
import SectionLegalBasis from '@/content/SectionLegalBasis';
import SectionComplaint from '@/content/SectionComplaint';

export default {
  name: 'StaticIntorduction',
  components: {
    Collapsible,
    Video,
    SectionWho,
    SectionPurpose,
    SectionRelationship,
    SectionBenfits,
    SectionWillIBenefit,
    SectionWhatIDo,
    SectionWhatInformation,
    SectionLegalBasis,
    SectionComplaint,
  },
  methods: {
    collapseOthers(excludeName) {
      for (const collapsibleName in this.$refs) {
        if (collapsibleName != excludeName) {
          this.$refs[collapsibleName].collapse();
        }
      }
    },
  },
};
</script>
