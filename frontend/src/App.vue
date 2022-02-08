<template>
<nav>
  <div class="flexbox">
    <router-link to="/favourites">
      <button class="favbutton">Favourites *</button>
    </router-link>
    <router-link to="/">
      <img src="../src/assets/logo.png" style="height:100%;">
    </router-link>
    <router-link to="/cart">
      <div id="cartContainter">
      <div id="cartValue">$27.01</div>
      <img src="../src/assets/cart.png" style="height:inherit;"/>
      <div id="cartItemNumber">2</div>
      </div>
    </router-link>
  </div>
</nav>
  <router-view/>
</template>

<style>
.body{
  padding: 0;
  margin: 0;
}

nav{
  background-color: green;
  height: 12vh;
  box-shadow: black 0px 2px 18px 0px;
  margin-bottom: 2%;
}

.flexbox{
  display: flex;
  height: inherit;
  justify-content: space-between;
}

.favbutton{
  float: left;
}

#cartContainter{
  float: right;
  height: 75%;
}

#cartValue{
  color: darkblue;
}

#cartItemNumber{
  color: red;;
}
</style>
