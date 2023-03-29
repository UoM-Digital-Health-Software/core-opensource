export const ConstantService = {
  get API_URL() {
    if (process.env.NODE_ENV === 'development') {
      return 'http://localhost:8080/api/';
    } else {
      return '/api/';
    }
  },

  STORAGE_KEY: 'CMHDS',
};
