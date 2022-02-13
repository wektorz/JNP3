<template>
    <div>
    <h1>YOUR CART</h1>
    <div class="flex">
    <button class="buybutton" @click="buy"> BUY </button>
    </div>
    <div class="products">
      <div v-for="product in this.items" :key="product.id" class="product">
        <ProductCard 
        @addToCart="addToCart"
        @deleteFromCart="deleteFromCart" 
        :authed="authed" 
        :hideQuantity="false" 
        :hideDelete="false" 
        :id="product.id"
        :desc="product.desc"
        :price="product.price"
        :img="product.img" 
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
    this.items = await this.axios.get(`http://localhost:81/api/cart?cookie=${this.cookie}&login=${this.login}`).then(response => response.data);
    console.log(this.items);
  },
  methods: {
   async addToCart(id, price, n){
      const response = await this.axios.post('http://localhost:81/api/cart', 
      {cookie: this.cookie, login: this.login, itemId: id, quantity: n})
      this.$emit('updatePrice', price * n);
      this.$emit('updateCartQuantity', n);
    },
    deleteFromCart(id, price, n){
      this.axios.delete('http://localhost:81/api/cart', 
      {data: {cookie: this.cookie, login: this.login, itemId: id, quantity: n}})
      .then(this.items = this.items.filter(el => {return el.quantity > 1}))
      console.log(this.items);
      this.$emit('updatePrice', -price * n);
      this.$emit('updateCartQuantity', -n);
    },
    async buy() {
      const data = await this.axios.post('http://localhost:81/api/cart/buy', {cookie: this.cookie, login: this.login, cart: this.items})
      .then(() => {
        this.items = null;
        this.$emit('updatePrice', 'reset');
        this.$emit('updateCartQuantity', 'reset');
        })
      .catch(() => console.log("Purchase failed"));
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

.flex{
  display: flex;
  justify-content: center;
}
.buybutton{
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
.buybutton:hover{
  transition: all 0.2s;
  filter: invert();
}
</style>
