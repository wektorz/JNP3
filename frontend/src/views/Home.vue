<template>
  <div>
    <h1>HOME</h1>
    <div class="products">
      <ProductCard id="test1" :authed="authed" @addToCart="addToCart" :img="img"></ProductCard>
      <div v-for="i in 20" :key="i" class="product">
        <ProductCard :authed="authed" @addToCart="addToCart" :img="img"></ProductCard>
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from "@/components/ProductCard.vue";

export default {
  components: { ProductCard },
  name: "Home",
  props: ["login", "cookie", "authed"],
  data() {
    return {
      img: "fork.jpg",
    };
  },
  methods:{
    async addToCart(id, price, n){
      const response = await this.axios.post('http://localhost:10001/api/cart', 
      {cookie: this.cookie, login: this.login, itemId: id, quantity: n}).then(response => response.status);
      if (response != 200)
        return;
      this.$emit('updatePrice', price * n);
      this.$emit('updateCartQuantity', n);
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
