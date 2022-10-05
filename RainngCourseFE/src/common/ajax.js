import Config from "./config";
import axios from "axios";
import qs from "qs";
import crypto from "crypto";
import { Buffer } from "buffer";

axios.defaults.withCredentials = true;
axios.interceptors.response.use(
  response => {
    return response;
  },
  error => {
    return Promise.reject(error);
  }
);

export const pureget = function(url) {
  return axios.get(Config.backEndUrl + url);
};

export const get = function(url, params) {
  return axios.get(Config.backEndUrl + url, {
    params: params
  });
};

export const pre_post = function(url, key, iv, ciphertext) {
  let formData = new FormData();
  formData.append("key", key);
  formData.append("iv", iv);
  formData.append("ciphertext", ciphertext);
  return axios.post(Config.backEndUrl + url, formData, {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  });
};
export const post = function(url, formData) {
  return axios.post(Config.backEndUrl + url, qs.stringify(formData), {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  });
};

export const del = function(url, params) {
  return axios.delete(Config.backEndUrl + url, {
    params: params
  });
};

export const put = function(url, data) {
  return axios.put(Config.backEndUrl + url, data, {
    headers: {
      "Content-Type": "application/x-www-form-urlencoded"
    }
  });
};

export const AES_decrypt = function(ciphertext, key) {
  try {
    const tmpSerect = Buffer.from(ciphertext, "base64");
    const pwd = Buffer.from(key, "hex");
    // 读取数组
    const iv = tmpSerect.slice(0, 12);
    const cipher = crypto.createDecipheriv("aes-128-gcm", pwd, iv);
    // 这边的数据为 去除头的iv12位和尾部的tags的16位
    return cipher.update(tmpSerect.slice(12, tmpSerect.length - 16));
  } catch (e) {
    console.log("Decrypt is error", e);
    return null;
  }
};

export const AES_encrypt = function(plaintext, key, iv) {
  try {
    const cipher = crypto.createCipheriv("aes-128-gcm", key, iv);

    let enc = cipher.update(plaintext, "utf8", "hex");
    enc += cipher.final("hex");
    const tags = cipher.getAuthTag();
    enc = Buffer.from(enc, "hex");
    const totalLength = iv.length + enc.length + tags.length;
    const ciphertext = Buffer.concat([iv, enc, tags], totalLength);
    return ciphertext;
  } catch (e) {
    console.log("Encrypt is error", e);
    return null;
  }
};
