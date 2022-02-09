<template>
  <div>
    <h1>HOME</h1>
    <button @click="testshop()">test</button>
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
      test: ""
    };
  },
  methods:{
    async testshop(){
      this.test = await this.axios.get('http://localhost:10001/api/shop').then(response => response.data)
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
