import axios from 'axios';
const cookies = require('browser-cookies');

import { ConstantService } from '@/services/constant.service';

export const ApiService = {
  initialise() {
    axios.defaults.baseURL = ConstantService.API_URL;
    axios.defaults.withCredentials = true;

    axios.interceptors.request.use(function (config) {
      config.headers['X-XSRF-TOKEN'] = cookies.get('XSRF-TOKEN');
      return config;
    });
  },

  query(resource, params, config) {
    if (config) {
      config = { params, ...config };
    } else {
      config = { params };
    }
    return axios.get(resource, config).catch((error) => {
      throw new Error(`PPIE ApiService ${error}`);
    });
  },

  getTemplateFromSlug(slug, resource) {
    if (slug === '') {
      return `${resource}`;
    } else {
      return `${resource}/${slug}`;
    }
  },

  get(resource, slug = '') {
    var template = this.getTemplateFromSlug(slug, resource);
    return axios.get(template).catch((error) => {
      throw new Error(`PPIE ApiService ${error}`);
    });
  },

  post(resource, params) {
    return axios.post(`${resource}`, params);
  },

  postFormData(url, formData) {
    return axios({
      method: 'post',
      url: url,
      data: formData,
      headers: { 'Content-Type': 'multipart/form-data' },
    });
  },

  update(resource, slug, params) {
    return axios.put(`${resource}/${slug}`, params);
  },

  put(resource, params) {
    return axios.put(`${resource}`, params);
  },

  delete(resource) {
    return axios.delete(resource).catch((error) => {
      throw new Error(`[RWV] ApiService ${error}`);
    });
  },
};
