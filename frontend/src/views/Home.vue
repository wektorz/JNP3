<template>
<div>
  <div class="products">
  <div class="product">
  <ProductCard></ProductCard>
  </div>
  <div class="product">
  <ProductCard></ProductCard>
  </div>
  <div class="product">
  <ProductCard></ProductCard>
  </div>
  <div class="product">
  <ProductCard></ProductCard>
  </div>
  <div class="product">
  <ProductCard></ProductCard>
  </div>
  <div class="product">
  <ProductCard></ProductCard>
  </div>
  <div class="product">
  <ProductCard></ProductCard>
  </div>
  </div>
</div>
</template>

<script>
import ProductCard from '@/components/ProductCard.vue'

export default {
  components: { ProductCard },
  name: 'Home'
}
</script>

<style scoped>

.products{
  margin-left: 15%;
  margin-right: 15%;
  display: flex
}

.product{
  justify-self: center;
  align-self: center;
}

.card{

}
</style>
