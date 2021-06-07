<template>

  <form action="#">
    <div class="field">
      <span class="fa fa-user"></span>
      <input type="text" v-bind:value="re_username"  readonly="readonly" placeholder="username">
    </div>
    <div class="field key"><!--这就是html中的属性继承，会继承field中的所有属性，并且可以通过.space为其增加属性-->
      <span class="fa fa-key"></span>
      <input :type="[isHide?'text':'password']" class="pass-key" v-bind:value="re_password"  readonly="readonly" placeholder="password">
      <span class="show" @click="hideOrShow">{{ message }}</span>
    </div>
    <div class="field code">
      <span class="fa fa-code"></span>
      <input type="text" v-bind:value="re_StatusCode"  readonly="readonly" placeholder="Status code">
    </div>
    <div class="forget">.</div>

  </form>
</template>


<script>

export default {
  name: 'MessageInfo',
  data() {
    return {
      isHide: true,
      message: "Hide",
      front: true,
      re_username: window.localStorage.getItem("username"),
      re_password: window.localStorage.getItem("password"),
      re_StatusCode: window.localStorage.getItem("statusCode")
    };
  },
  methods: {
    hideOrShow() {
      this.isHide = !this.isHide;
      if (this.isHide) {
        this.message = "Hide";
      } else {
        this.message = "Show";
      }
    },
  }
}
</script>




<style>
.field {
  position: relative;
  height: 45px;
  width: 100%;
  display: flex;
  background-color: #e6e6e6;
  border-radius: 16px;
}

.field span {
  color: #222;
  width: 40px;
  line-height: 45px;
  /*使用line-height可用于进行垂直据居中操作，
  我们把一段文本的line-height设置为父容器的高度就可以实现文本垂直居中了*/
  /*
  想要文本水平居中设置text-align：center即可。
  */
}

.field .show {
  font-size: 15px;
  font-weight: bold;
  color: #096dd9;
  transform: translateX(-15px);

}

.field input {
  height: 100%;
  width: 100%;
  background: transparent; /*背景设置成透明使其和div容器的颜色统一*/
  border: none;
  outline: none;
  color: #222;
}
.code{
  margin-top: 16px;
}

.key {
  margin-top: 16px;
}

.login {
  position: relative;
  background-color: #e6e6e6;
  border-radius: 16px;
  width: 50%;
  height: 30px;
  transform: translateX(50%);
  margin-top: 20px;


}

.login:active {
  background-color: #bfbfbf;
}

.login input {
  font-size: 15px;
  font-weight: bold;
  border: none;
  background: transparent;
  line-height: 30px /*使用lineheigh使得文本垂直居中*/;

  color: #2c3e50;

}

.forget {
  text-align: left; /*注意点：设置==块级元素内==文本的水平对齐方式，注意是"块级元素内的文本！"，并不能将该属性放在a标签中！*/
  margin-top: 10px;
  margin-bottom: 5px;
  position: relative;
  left: 5px;
  color: transparent;
}



</style>
