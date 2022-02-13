<template>
  <nav>
    <router-link to="/">
      <div class="logo">
        <img style="height: 100%" src="../src/assets/logo.png" />
      </div>
    </router-link>
    <div class="flexbox">
      <div>
        <button class="navbutton"
          v-if="!authed"
          @click="
            showForm = true;
            loginForm = true;
          "
        >
          Login
        </button>
        <button class="navbutton"
          v-if="!authed"
          @click="
            showForm = true;
            loginForm = false;
          "
        >
          Register
        </button>
        <router-link v-if="authed" to="/">
          <button class="navbutton" @click="logout">Logout</button>
        </router-link>
        <router-link v-if="authed" to="/favourites">
          <button class="navbutton">Favourites &#10084;</button>
        </router-link>
      </div>
    </div>
      <router-link v-if="authed" to="/cart">
        <div id="cartContainter">
          <div id="cartValue">{{ Math.floor(cartSum * 100) / 100 }}zł</div>
          <img src="../src/assets/cart.png" style="height: inherit" />
          <div id="cartItemNumber">{{ cartItemsQuantity }}</div>
        </div>
      </router-link>
    
  </nav>
  <Form
    :text="loginForm ? 'Login' : 'Register'"
    :isLogin="loginForm"
    v-model:showForm="showForm"
    v-show="showForm"
    @authed="auth"
  ></Form>
  <router-view
    :authed="authed"
    :cookie="cookie"
    :login="login"
    @updatePrice="updatePrice"
    @updateCartQuantity="updateCartQuantity"
  />
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
      login: "",
      cookie: "",
      cartSum: 0,
      cartItemsQuantity: 0,
    };
  },
  async created() {
    this.syncCartData();
  },
  beforeUnmount() {
    this.axios.post("http://localhost:10002/api/logout", {
      login: this.login,
      cookie: this.cookie,
    });
  },
  methods: {
    updatePrice(add) {
      if (add == "reset") {
        this.cartSum = 0;
        return;
      }
      this.cartSum += add;
      if (this.cartSum < 0) this.cartSum = 0;
    },
    updateCartQuantity(add) {
      if (add == "reset") {
        this.cartItemsQuantity = 0;
        return;
      }
      this.cartItemsQuantity += add;
      if (this.cartItemsQuantity < 0) this.cartItemsQuantity = 0;
    },
    async syncCartData() {
      const data = await this.axios
        .get(
          `http://localhost:81/api/cart?cookie=${this.cookie}&login=${this.login}`
        )
        .then((response) => response.data);
      this.cartSum = data
        .map((product) => product.price * product.quantity)
        .reduce((acc, price) => price + acc);
      this.cartItemsQuantity = data
        .map((product) => product.quantity)
        .reduce((acc, quantity) => quantity + acc);
    },
    auth(cookie, login) {
      this.authed = true;
      this.cookie = cookie;
      this.login = login;
      this.syncCartData();
    },
    async logout() {
      console.log("logout");
      const response = await this.axios
        .post("http://localhost:10002/api/logout", {
          login: this.login,
          cookie: this.cookie,
        })
        .then(() => {
          this.authed = false;
          this.cookie = "";
          this.login = "";
          this.cartItemsQuantity = 0;
          this.cartSum = 0;
        })
        .catch(() => console.log("Couldnt logout"));
    },
  },
};
</script>

<style>
.body {
  padding: 0;
  margin: 0;
}

nav {
  position: relative;
  background-color: #2d7d95;
  height: 12vh;
  box-shadow: black 0px 2px 18px 0px;
  margin-bottom: 2%;
}

.flexbox {
  display: flex;
  height: inherit;
  align-items: center;
  justify-content: center;
  position: absolute;
  width: 100%;
}

#cartContainter {
  float: right;
  height: 75%;
  margin-right: 20px;
  text-align: center;
  margin-top: 5px;
  cursor: pointer;
  position: relative;
  z-index: 2;
}

#cartValue {
  color: white;
  background-color: #6fd58e;
  border-radius: 5px;
}

#cartItemNumber {
  color: white;
  background-color: #bf4388;
  border-radius: 5px;
}

.logo {
  cursor: pointer;
  height: 100%;
  float: left;
  margin-left: 20px;
  position: absolute;
  z-index: 2;
}

.navbutton {
  background-color: #f50675;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 10px;
  box-shadow: 0 0 7px 1px #3e3e3e;
  cursor: pointer;
}

.navbutton:hover{
  transition: all 0.2s;
  filter: invert();
}

</style>
