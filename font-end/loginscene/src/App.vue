<template>
  <div id="app">
    <head>
      <meta charset="UTF-8">
      <title>登陆界面</title>
      <link href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
      <!--通过网站引入第三方图标-->
    </head>
    <body>
    <div class="background-img">
      <div class="InContents">
        <div class="indexContainer">
          <div class="routerLink">
            <router-link @click.native="isLogin" v-bind:class="[isLoginPage? 'activePage' : 'unActivePage']" to="/">
              Login
            </router-link>
            <span class="separator"> / </span>
            <router-link @click.native="isSignUp" v-bind:class="[isLoginPage? 'unActivePage' : 'activePage']"
                         to="/SignUpPage">Sign Up
            </router-link>
          </div>
          <router-view class="view"></router-view>
        </div>
      </div>
      <div class="OutContents">
        <div class="indexContainer">
          <div class="message">
            Response Data
          </div>
          <div>
            <MessageInfo></MessageInfo>
          </div>
        </div>
      </div>
    </div>

    </body>
  </div>
</template>


<script>
/*错因：文件目录写错导致找不到具体文件*/
import MessageInfo from "../src/components/MessageInfo"
export default {
  components:{
    MessageInfo
  },
  data() {
    return {
      isLoginPage: true
    }
  },
  methods: {
    isLogin() {
      this.isLoginPage = true;
      console.log(this.isLoginPage)
    },
    isSignUp() {
      this.isLoginPage = false;
      console.log(this.isLoginPage)
    }
  }
}
</script>


<style>
* {
  margin: 0; /*修改系统默认的内外边距*/
  padding: 0;
  box-sizing: border-box;
  user-select: none; /*无法选中，整体感更强*/
}

.background-img {
  background: url("./assets/bgimg.jpg") no-repeat;
  background-size: cover;
  background-position: center;
  background-attachment: fixed; /*置固定的背景图像不随之滚动*/
  /*设置视口的宽度和高度为100%，即铺满整个页面*/
  height: 100vh;
  width: 100vw;
}

.InContents {
  position: absolute;
  top: 50%;
  left: 30%;
  transform: translate(-50%, -50%);
  width: 380px;

  display: flex;
  justify-content: center;
  text-align: center;

  background: rgba(255, 255, 255, 0.1);

  perspective: 1000px;
  box-shadow: -1px 4px 28px 0px rgba(0, 0, 0, 0.7); /*设置阴影部分*/
  opacity: 0.90; /*设置透明度*/

}

.OutContents{
  position: absolute;
  top: 50%;
  left: 70%;
  transform: translate(-50%, -50%);
  width: 380px;

  display: flex;
  justify-content: center;
  text-align: center;

  background: rgba(255, 255, 255, 0.1);

  perspective: 1000px;
  box-shadow: -1px 4px 28px 0px rgba(0, 0, 0, 0.7); /*设置阴影部分*/
  opacity: 0.90; /*设置透明度*/

}


.indexContainer {
  width: 80%;
  margin-top: 40px;
  margin-bottom: 40px;
}

.indexContainer .routerLink {
  color: burlywood; /*#e6d8d8 bisque*/
  font-size: 30px;
  font-weight: 600;
  margin: 0 0 35px 0; /*上右下左，*/
}

.indexContainer .message{
  color: burlywood; /*#e6d8d8 bisque*/
  font-size: 30px;
  font-weight: 600;
  margin: 0 0 35px 0; /*上右下左，*/
}


.indexContainer .activePage {
  text-decoration: none;
  color: burlywood;
}

.indexContainer .unActivePage {
  text-decoration: none;
  color: darkgrey;
}
</style>


