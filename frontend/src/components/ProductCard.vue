<template>
     <div class="product">
      <div class="card">
      <img :src="img" style="width:100%; border-radius:10px 10px 0 0"/>
      <p class="desc">{{desc}}</p>
      </div>
      <div class="price">
        ${{price}}
      </div>
      <div v-if="!hideQuantity && authed" class="quantity">
        {{quantity}}
      </div>
      <div v-if="!hideFav && authed" class="favBtn">
        <button @click="emitMakeFavourite">*</button>
      </div>
      <div v-if="!hideComp && authed" class="compBox">
        <input type="checkbox">
      </div>
      <div v-if="!hideDelete && authed" class="delBtn">
        <button @click="emitDeleteCart">X</button>
      </div>
      <div v-if="!hideCart && authed" class="addCart">
        <button @click="emitAddCart">+</button>
      </div>
    </div>
</template>

<script>

export default {
  name: 'ProductCard',
  props: {
    hideFav: {default: false},
    hideComp: {default: true},
    hideDelete: {default: true},
    hideCart: {default: false},
    hideQuantity: {default: true},
    id: {default: "siusiak"},
    price: {default: 2},
    img: {default: ""},
    desc: {default: "AMOGUS"},
    quantity: {default: 0},
    authed: {default: false}
  },
  methods: {
    emitAddCart() {
      console.log(this.id);
      this.$emit('addToCart', this.id, this.price, 1);
      this.$emit('update:quantity', this.quantity + 1);
    },
    emitDeleteCart() {
      this.$emit('deleteFromCart', this.id, this.price, 1);
      this.$emit('update:quantity', this.quantity - 1 < 0 ? 0 : this.quantity - 1);
    }
  }
}
</script>

<style scoped>

.card{
    box-shadow: #00000080 0 0 15px 1px;
    background-color: ivory;
    border-radius: 10px;
    display: inline-block;

}
.product{
  position: relative;
}

.price{
  background-color: red;
  border-radius: 100px;
  display: inline-block;
  right: -10px;
  position: absolute;
  top: -10px;
  box-shadow: red 0 0 0 10px;
}

.favBtn{
  display: inline-block;
  left: -10px;
  position: absolute;
  top: -10px;
}

.compBox{
  display: inline-block;
  left: -10px;
  position: absolute;
  top: -10px;
}

.delBtn{
  background-color: red;
  border-radius: 100px;
  color: gold;
  display: inline-block;
  left: -10px;
  position: absolute;
  top: -10px;
}

.addCart{
  display: inline-block;
  right: -10px;
  position: absolute;
  bottom: -10px;
  background-color: green;
}


.quantity{
  border-radius: 100px;
  background-color: blue;
  box-shadow: blue 0 0 0 10px;
  display: inline-block;
  left: -10px;
  position: absolute;
  bottom: -10px;
}

.desc{
    overflow-wrap: break-word;
}
</style>
