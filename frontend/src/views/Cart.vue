<template>
    <div style="background-color: #ffffff">
    <h1>YOUR CART</h1>
    <button> BUY </button>
    <div class="products">
      <div v-for="product in this.items" :key="product.id" class="product">
        <ProductCard @addToCart="addToCart"
        @deleteFromCart="deleteFromCart" 
        :authed="authed" 
        :hideQuantity="false" 
        :hideDelete="false" 
        :hideFav="true" 
        :id="product.id"
        :desc="product.desc"
        :price="product.price"
        img="fork.jpg" 
        v-model:quantity="product.quantity"></ProductCard>
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from "@/components/ProductCard.vue";

export default {
  components: {ProductCard},
  name: 'Cart',
  props: ["login", "cookie", "authed"],
  data() {
    return {
      items: null,
    };
  },
  async created () {
    this.items = await this.axios.get(`http://localhost:8080/api/cart?cookie=${this.cookie}&login=${this.login}`).then(response => response.data);
    console.log(this.items);
  },
  methods: {
   async addToCart(id, price, n){
      const response = await this.axios.post('http://localhost:8080/api/cart', 
      {cookie: this.cookie, login: this.login, itemId: id, quantity: n}).then(response => response.status);
      if (response != 200)
        return;
      this.$emit('updatePrice', price * n);
      this.$emit('updateCartQuantity', n);
    },
    async deleteFromCart(id, price, n){
      const response = await this.axios.delete('http://localhost:8080/api/cart', 
      {data: {cookie: this.cookie, login: this.login, itemId: id, quantity: n}}).then(response => response.status)
      if (response != 200)
        return;
      console.log(price);
      console.log(n);
      this.$emit('updatePrice', -price * n);
      this.$emit('updateCartQuantity', -n);
    }
  }
}
</script>

<style scoped>
h1 {
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
