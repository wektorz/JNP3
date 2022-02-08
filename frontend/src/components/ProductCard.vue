<template>
     <div class="product">
      <div class="card">
      <img :src="imgUrl()" style="width:100%; border-radius:10px 10px 0 0"/>
      <p class="desc">Lorem ipsulasjdiofjaoisjfioajfo[asdjfoiajfoiapfjaspiodfjm</p>
      </div>
      <div class="price">
        $22
      </div>
      <div v-if="!hideFav" class="favBtn">
        <button @click="makeFavourite">*</button>
      </div>
      <div v-if="!hideComp" class="compBox">
        <input type="checkbox">
      </div>
      <div v-if="!hideDelete" class="delBtn">
        <button @click="deleteFromCart">X</button>
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
    id: {default: -1},
    img: {default: ""},
    desc: {default: ""}
  },
  methods: {
    makeFavourite(){
      this.axios
          .post(
            "http://favourites_service:8080/api/fav",
            { id: this.id},
            {
              headers: {
                "Content-Type":
                  "application/x-www-form-urlencoded; charset=UTF-8",
              },
            }
          )
          .then((response) => {console.log(response); if (response.status == 200) this.$emit('update:authed', true);});
    },
    deleteFromCart(){
    },
    imgUrl() {
      return require('@/assets/' + this.img);
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

.desc{
    overflow-wrap: break-word;
}
</style>
