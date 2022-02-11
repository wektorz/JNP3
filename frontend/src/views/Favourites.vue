<template>
  <div style="background-color: #ffffff">
    <h1>FAVOURITES</h1>
    <button @click="compare"> COMPARE </button>
    <div class="products">
      <div v-for="product in this.items" :key="product.id" class="product">
        <ProductCard 
        @addToCart="addToCart"
        @deleteFromCart="deleteFromCart" 
        :authed="authed"
        :hideComp="false"  
        :hideDelete="false" 
        :hideFav="true" 
        :id="product.id"
        :desc="product.desc"
        :price="product.price"
        :img="product.img" ></ProductCard>
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from "@/components/ProductCard.vue";
export default {
  components: { ProductCard },
  name: "Favourites",
  props: ["login", "cookie", "authed"],
  data() {
    return {
      items: null,
    };
  },
  async created() {
    this.items = await this.axios.get(`http://localhost:10000/api/fav?cookie=${this.cookie}&login=${this.login}`).then(response => response.data);
    console.log(this.items);
  },
};
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