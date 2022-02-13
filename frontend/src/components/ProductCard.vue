<template>
     <div class="product">
      <div class="card">
      <img :src="img" style="width:100%; border-radius:10px 10px 0 0"/>
      <p class="desc">{{desc}}</p>
      </div>
      <div class="price">
        {{price}}zł
      </div>
      <div v-if="!hideQuantity && authed" class="quantity">
        {{quantity}}
      </div>
      <div v-if="!hideFav && authed" class="favBtn">
        <button class="fav" @click="emitAddFav">&#10084;</button>
      </div>
      <div v-if="!hideDelete && authed" class="delBtn">
        <button class="del" @click="emitDeleteCart">X</button>
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
    id: {default: ""},
    price: {default: 2},
    img: {default: ""},
    desc: {default: ""},
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
    },
    emitAddFav() {
      console.log(this.id);
      this.$emit('addToFav', this.id);
    },
    emitDeleteFav() {
      this.$emit('deleteFromFav', this.id);
    }
  }
}
</script>

<style scoped>

.card{
    box-shadow: #00000080 0 0 15px 1px;
    background-color: #db7ccfc9;
    border-radius: 10px;
    display: inline-block;

}
.product{
  position: relative;
}

.price{
  background-color: #926ee7;
  border-radius: 5px;
  display: inline-block;
  right: -10px;
  position: absolute;
  top: -10px;
  box-shadow: #926ee7 0 0 0 10px;
  color: white;
}

.favBtn{
  display: inline-block;
  left: -10px;
  position: absolute;
  top: -10px;
  height: 40px;
  width: 40px;
  line-height: 38px;
  text-align: center;
  padding: 0;
  vertical-align: top;
  border: 1px solid #ff4c4c;
  background-color: #ff4c4c;
  color: #fff;
}

.delBtn{
  display: inline-block;
  left: -10px;
  position: absolute;
  top: -10px;
  height: 40px;
  width: 40px;
  line-height: 38px;
  text-align: center;
  padding: 0;
  vertical-align: top;
  border: 1px solid #ff7330;
  background-color: #ff7330;
  color: #fff;
}

.addCart{
  display: inline-block;
  right: -10px;
  position: absolute;
  bottom: -10px;
  height: 40px;
  width: 40px;
  line-height: 38px;
  text-align: center;
  padding: 0;
  vertical-align: top;
  border: 1px solid #a1a1a1;
  background-color: #a1a1a1;
  color: #fff;
}


.quantity{
  border-radius: 2px;
  background-color: #47b57e;
  box-shadow: #47b57e 0 0 0 10px;
  display: inline-block;
  left: -10px;
  position: absolute;
  bottom: -10px;
}

.desc{
    overflow-wrap: break-word;
    text-align: center;
}

button{
  background-image: none;
  background: transparent;
  border: 1px solid transparent;
  color: #fff;
  cursor: pointer;
  display: inline-block;
  font-family: 'Montserrat','Helvetica Neue',Helvetica,Arial,sans-serif;
  font-weight: 700;
  margin: 0;
  font-size: 1.2rem;
  line-height: 1.6rem;
  box-sizing: border-box;
  vertical-align: middle;
}


.addCart:hover{
  filter: invert(1);
  transition: all 0.2s;
}
.delBtn:hover{
  transition: all 0.2s;
  filter: invert(1);
}
.favBtn:hover{
  transition: all 0.2s;
  filter: invert(1);
}
</style>
