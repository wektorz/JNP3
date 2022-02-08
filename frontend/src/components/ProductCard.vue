<template>
     <div class="product">
      <div class="card">
      <img src="../assets/fork.jpg" style="width:100%; border-radius:10px 10px 0 0"/>
      <p class="desc">Lorem ipsulasjdiofjaoisjfioajfo[asdjfoiajfoiapfjaspiodfjm</p>
      </div>
      <div class="price">
        $22
      </div>
    </div>
</template>

<script>

export default {
  name: 'ProductCard'
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
}

.desc{
    overflow-wrap: break-word;
}
</style>
