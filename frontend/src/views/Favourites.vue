<template>
  <div style="background-color: #ffffff">
    <h1>FAVOURITES</h1>
    <button> COMPARE </button>
    <div class="products">
      <div v-for="i in 16" :key="i" class="product">
        <ProductCard :hideFav="true" :hideComp="false" :img="img"></ProductCard>
      </div>
    </div>
  </div>
</template>

<script>
import ProductCard from "@/components/ProductCard.vue";
export default {
  components: { ProductCard },
  name: "Favourites",
  data() {
    return {
      items: null,
      img: "fork.jpg",
    };
  },
  async created() {
    //const items = await this.axios.get(`http://favourites_service:8080/api/fav?id=${this.id}`).then(response => response.data);
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