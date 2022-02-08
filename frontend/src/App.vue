<template>
<nav>
  <div class="flexbox">
  <div>
  <button v-if="!authed" @click="showForm=true;loginForm=true;">
    Login
  </button>
   <button v-if="!authed" @click="showForm=true;loginForm=false;">
    Register
    </button>
    <button v-if="authed" @click="authed=false">
    Logout
    </button>
    <router-link to="/favourites">
      <button class="favbutton">Favourites *</button>
    </router-link>
  </div>
    <router-link to="/">
      <img src="../src/assets/logo.png" style="height:100%;">
    </router-link>
    <router-link to="/cart">
      <div id="cartContainter">
      <div id="cartValue">$27.01</div>
      <img src="../src/assets/cart.png" style="height:inherit;"/>
      <div id="cartItemNumber">2</div>
      </div>
    </router-link>
  </div>
</nav>
  <Form :text="loginForm ? 'Login' : 'Register'"
        :isLogin="loginForm" 
        v-model:showForm="showForm"
        v-model:authed="authed" 
        v-show="showForm"></Form>
  <router-view/>
</template>

<script>
import Form from '@/components/Form.vue'
export default {
  components: { Form },
  name: 'App',
  data () {
    return {
      showForm: false,
      loginForm: true,
      authed: false 
    }
  }
}
</script>

<style>
.body{
  padding: 0;
  margin: 0;
}

nav{
  background-color: green;
  height: 12vh;
  box-shadow: black 0px 2px 18px 0px;
  margin-bottom: 2%;
}

.flexbox{
  display: flex;
  height: inherit;
  justify-content: space-between;
}

.favbutton{
  float: left;
}

#cartContainter{
  float: right;
  height: 75%;
}

#cartValue{
  color: darkblue;
}

#cartItemNumber{
  color: red;;
}
</style>
