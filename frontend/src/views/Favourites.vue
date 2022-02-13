<template>
  <div>
    <h1>FAVOURITES</h1>
    <div class="flex">
    <button class="favbutton" @click="sort('desc')"> SORT &#8595; </button>
    <button class="favbutton" @click="sort('asc')"> SORT &#8593; </button>
    </div>
    <div class="products">
      <div v-for="product in this.items" :key="product.id" class="product">
        <ProductCard 
        @addToCart="addToCart"
        @deleteFromCart="deleteFromFav" 
        :authed="authed"
        :hideDelete="false" 
        :hideFav="true"
        :hideCart="true" 
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
  methods: {
    deleteFromFav(id) {
      this.axios.delete('http://localhost:10000/api/fav', 
      {data: {cookie: this.cookie, login: this.login, itemId: id}})
      .then(this.items = this.items.filter(el => {return el.id != id}))
    },
    async sort(type) {
      this.items = await this.axios.get(`http://localhost:10000/api/fav/sort?cookie=${this.cookie}&login=${this.login}&type=${type}`).then(response => response.data);
    }
  }
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

.flex{
  display: flex;
  justify-content: center;
}
.favbutton{
  background-color: #0af98a;
  border-style: double;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  margin: 10px;
  box-shadow: 0 0 10px 2px #6a6a6a;
  cursor: pointer;
  margin-bottom: 3%;
}
.favbutton:hover{
  transition: all 0.2s;
  filter: invert();
}
</style>