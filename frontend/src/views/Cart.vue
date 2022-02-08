<template>
    <div style="background-color: #ffffff">
    <h1>YOUR CART</h1>
    <div class="products">
      <div v-for="i in 16" :key="i" class="product">
        <ProductCard :hideDelete="false" :hideFav="true" :img="img"></ProductCard>
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from "@/components/ProductCard.vue";

export default {
  components: {ProductCard},
  name: 'Cart',
  data() {
    return {
      items: null,
      img: "fork.jpg",
    };
  },
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
