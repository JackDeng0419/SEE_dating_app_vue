import Vue from "vue";
import VueRouter from "vue-router";
import Container from "./views/Container.vue";
import Login from "./views/Login";
import Home from "./views/MyProfile";
import Test from "./views/test";
import WhoLikesMe from "./views/WhoLikesMe.vue";
import MutualLikes from "./views/MutualLikes.vue";
import MyLikes from "./views/MyLikes.vue";
import MyDislikes from "./views/MyDislikes.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "container",
    component: Container,
    children: [
      {
        path: "/home",
        name: "home",
        component: Home
      },
      {
        path: "/test",
        name: "test",
        component: Test
      },
      {
        path: "/who-liked-me",
        name: "who-liked-me",
        component: WhoLikesMe
      },
      {
        path: "/mutual-liked",
        name: "mutual-liked",
        component: MutualLikes
      },
      {
        path: "/my-liked",
        name: "my-liked",
        component: MyLikes
      },
      {
        path: "/my-disliked",
        name: "my-disliked",
        component: MyDislikes
      }
    ]
  },
  {
    path: "/login",
    name: "login",
    component: Login
  }
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
