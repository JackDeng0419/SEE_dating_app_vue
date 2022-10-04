import Vue from "vue";
import ElementUI from "element-ui";
import VueClipboard from "vue-clipboard2";
import ECharts from "vue-echarts";
import "element-ui/lib/theme-chalk/index.css";
import App from "./App.vue";
import router from "./router";

Vue.config.productionTip = false;

Vue.use(ElementUI);
Vue.use(VueClipboard);

Vue.component("v-chart", ECharts);
new Vue({
  router,
  render: h => h(App)
}).$mount("#app");
