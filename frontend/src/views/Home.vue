<template>
  <div>
    <h1>HOME</h1>
    <div class="products">
      <div v-for="product in items" :key="product.id" class="product">
        <ProductCard 
        @addToCart="addToCart"
        @addToFav="addToFav"
        :authed="authed"   
        :id="product.id"
        :desc="product.desc"
        :price="product.price"
        :img="product.img"></ProductCard>
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
      items: null
    };
  },
  async created () {
    this.items = await this.axios.get(`http://localhost:80/api/products`)
    .then(response => response.data)
    .catch(() => console.log("Couldn't load products"));
  },
  methods:{
    async addToCart(id, price, n){
      const response = await this.axios.post('http://localhost:81/api/cart', 
      {cookie: this.cookie, login: this.login, itemId: id, quantity: n}).then(response => response.status);
      if (response != 200)
        return;
      this.$emit('updatePrice', price * n);
      this.$emit('updateCartQuantity', n);
    },
    addToFav(id){
      this.axios.post('http://localhost:10000/api/fav', {cookie: this.cookie, login: this.login, itemId: id})
      .catch(() => console.log("Couldn't add to favourites"));
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
  row-gap: 1%;
  justify-content: center;
}

.product {
  justify-self: center;
  align-self: center;
}
</style>
