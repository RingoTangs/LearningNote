<template>
  <div class="dashboard">
    <h1>Dashboard</h1>
    <h2>
      <a href="https://www.sass.hk/docs/" target="_blank">sass官方文档</a>
    </h2>
    <ul class="list">
      <li>
        <router-link to="css-extensions">CSS 功能拓展 (CSS Extensions)</router-link>
      </li>
      <li>
        <router-link to="sass-script">Sass Script</router-link>
      </li>
      <li>
        <router-link to="roles-and-directives">RulesAndDirectives</router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Dashboard'
}
</script>

<style lang="scss" scoped>
.dashboard {
  width: 350px;
  margin: 100px auto;
  h1,
  h2 {
    text-align: center;
  }
}

.list {
  // background-color: pink;
  display: flex;
  flex-direction: column;
  align-items: center;
  li {
    // border: 1px solid black;
    width: 80%;
    margin-top: 15px;
    &:nth-last-child(1) {
      margin-bottom: 15px;
    }
    a {
      text-decoration: none;
      color: skyblue;
      transition: all 0.3s ease-in-out;
      &:hover {
        color: red;
      }
    }
  }
}
</style>
