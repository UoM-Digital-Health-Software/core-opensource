const STATIC_PAGE_COMPONENTS = {
  PAGE_STATIC_INTRODUCTION: 'StaticIntroduction',
  PAGE_DOWNLOAD_CONSENT: 'StaticDownloadConsent',
};

export const StaticPageService = {
  exists(pageName) {
    return pageName in STATIC_PAGE_COMPONENTS;
  },

  getComponentName(pageName) {
    return STATIC_PAGE_COMPONENTS[pageName];
  },
};
