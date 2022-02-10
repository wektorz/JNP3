<template>
  <div>
    <h1>HOME</h1>
    <button @click="testGet()">testGet</button>
    <button @click="testPost()">testPost</button>
    <button @click="testDelete()">testDelete</button>
    <input v-model="getLogin" type="text" placeholder="Login">
    <input v-model="getCookie" type="text" placeholder="Cookie">
    <p>{{test}}</p>
    <div class="products">
      <div v-for="i in 20" :key="i" class="product">
        <ProductCard :img="img"></ProductCard>
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from "@/components/ProductCard.vue";

export default {
  components: { ProductCard },
  name: "Home",
  data() {
    return {
      img: "fork.jpg",
      test: "",
      getLogin : "",
      getCookie : ""
    };
  },
  methods:{
    async testGet(){
      this.test = await this.axios.get(`http://localhost:8080/api/cart?cookie=${this.getCookie}&login=${this.getLogin}`).then(response => response.data)
    },
    async testPost(){
      this.test = await this.axios.post('http://localhost:8080/api/cart', {cookie: this.getCookie, login: this.getLogin, itemId: 10101, quantity: 3}).then(response => response.data)
    },
    async testDelete(){
      this.test = await this.axios.delete('http://localhost:8080/api/cart', {data: {cookie: this.getCookie, login: this.getLogin, itemId: 10101, quantity: 3}}).then(response => response.data)
    }
  }
};
</script>

<style scoped>

h1{
  text-align: center;
  margin-bottom: 3%;
}

.products {
  margin-left: 15%;
  margin-right: 15%;
  margin-bottom: 25%;
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr;
  column-gap: 5%;
  row-gap: 5%;
  justify-content: center;
}

.product {
  justify-self: center;
  align-self: center;
}
</style>
