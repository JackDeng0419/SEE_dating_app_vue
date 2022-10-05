import { post, pureget } from "@/common/ajax";
/*import crypto from 'crypto';
const plaintext = 'password';
const iv = crypto.randomBytes(12);
const key = crypto.randomBytes(16);*/
export const match = formdata => post("/login/check", formdata);

export const login = code => post("/login/login", { code: code });

export const signup = formdata => post("/login/signup", formdata);

export const getLoginStatus = () => {
  /*const ciphertext = AES_encrypt(plaintext, key, iv);
    pre_post("/login/status",
        key.toString("base64"),
        iv.toString("base64"),
        ciphertext.toString("base64")
    ).then((res) =>{
            const new_ciphertext = res.data["data"].userid;
        try {
            const tmpSerect = ;
            const pwd = Buffer.from(key, 'hex');
            // 读取数组
            var iv = tmpSerect.slice(0, 12)
            var cipher = crypto.createDecipheriv('aes-128-gcm', pwd, iv)
            // 这边的数据为 去除头的iv12位和尾部的tags的16位
            var msg = cipher.update(tmpSerect.slice(12, tmpSerect.length - 16))
            return msg.toString('utf8')
        } catch (e) {
            console.log("Decrypt is error", e)
            return null
        }
    });*/
  return pureget("/login/status");
};
