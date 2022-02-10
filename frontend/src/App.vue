<template>
  <nav>
    <div class="flexbox">
      <div>
        <button
          v-if="!authed"
          @click="
            showForm = true;
            loginForm = true;
          "
        >
          Login
        </button>
        <button
          v-if="!authed"
          @click="
            showForm = true;
            loginForm = false;
          "
        >
          Register
        </button>
        <router-link v-if="authed" to="/">
          <button @click="authed = false">Logout</button>
        </router-link>
        <button @click="authed = true">DEBUG AUTH</button>
        <router-link v-if="authed" to="/favourites">
          <button class="favbutton">Favourites *</button>
        </router-link>
      </div>
      <router-link to="/">
        <img src="../src/assets/logo.png" style="height: 100%" />
      </router-link>
      <router-link v-if="authed" to="/cart">
        <div id="cartContainter">
          <div id="cartValue">${{cartSum}}</div>
          <img src="../src/assets/cart.png" style="height: inherit" />
          <div id="cartItemNumber">{{cartItemsQuantity}}</div>
        </div>
      </router-link>
    </div>
  </nav>
  <Form
    :text="loginForm ? 'Login' : 'Register'"
    :isLogin="loginForm"
    v-model:showForm="showForm"
    v-show="showForm"
    @authed="auth"
  ></Form>
  <router-view :authed="authed" :cookie="cookie" :login="login" @updatePrice="updatePrice" @updateCartQuantity="updateCartQuantity"/>
</template>

<script>
import Form from "@/components/Form.vue";
export default {
  components: { Form },
  name: "App",
  data() {
    return {
      showForm: false,
      loginForm: true,
      authed: false,
      login: "jan",
      cookie: "siur",
      cartSum: 0,
      cartItemsQuantity: 0
    };
  },
  async created() {
    //auth
    //if authed
    this.syncCartData();
  },
  methods: {
    updatePrice(add) {
      this.cartSum += add;
      if(this.cartSum < 0)
        this.cartSum = 0; 
    },
    updateCartQuantity(add) {
      this.cartItemsQuantity += add;
      if(this.cartItemsQuantity < 0)
        this.cartItemsQuantity = 0;
    },
    async syncCartData() {
      const data = await this.axios.get(`http://localhost:8080/api/cart?cookie=${this.cookie}&login=${this.login}`).then(response => response.data);
      this.cartSum = data.map(product => (product.price * product.quantity)).reduce((acc, price) => price + acc);
      this.cartItemsQuantity = data.map(product => product.quantity).reduce((acc, quantity) => quantity + acc);
    },
    auth(cookie, login) {
      this.authed = true;
      this.cookie = cookie;
      this.login = login;
      this.syncCartData();
    }
  }
};
</script>

<style>
.body {
  padding: 0;
  margin: 0;
}

nav {
  background-color: green;
  height: 12vh;
  box-shadow: black 0px 2px 18px 0px;
  margin-bottom: 2%;
}

.flexbox {
  display: flex;
  height: inherit;
  justify-content: space-between;
}

.favbutton {
  float: left;
}

#cartContainter {
  float: right;
  height: 75%;
}

#cartValue {
  color: darkblue;
}

#cartItemNumber {
  color: red;
}
</style>
