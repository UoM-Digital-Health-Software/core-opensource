import { ConstantService } from '@/services/constant.service';
import { ref } from 'vue';

export const StoreService = {
  store: ref({
    machineId: null,
    responseId: null,
  }),

  initialise() {
    const storeJSON = window.localStorage.getItem(ConstantService.STORAGE_KEY);

    if (storeJSON != null) {
      this.store.value = JSON.parse(storeJSON);
    }
  },

  get(key) {
    return this.store.value[key];
  },

  set(key, value) {
    this.store.value[key] = value;
    this.save();
  },

  exists(key) {
    return this.get(key) != null;
  },

  clear(key) {
    this.set(key, null);
  },

  save() {
    const storeJSON = JSON.stringify(this.store.value);
    window.localStorage.setItem(ConstantService.STORAGE_KEY, storeJSON);
  },
};
