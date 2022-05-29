<template>
  <div>
    <h1>UserSearch</h1>
    <user-search-header />
    <user-search-list />
  </div>
</template>

<script>
  import UserSearchHeader from './UserSearchHeader.vue'
  import UserSearchList from './UserSearchList.vue'
  export default {
    name: 'UserSearch',
    components: { UserSearchHeader, UserSearchList },
  }
</script>

<style>
</style>