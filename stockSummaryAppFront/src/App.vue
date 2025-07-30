<template>
  <div>
    <h1>User List</h1>
    <ul>
      <li v-for="(user, index) in users" :key="index">{{ user }}</li>
    </ul>

    <input v-model="newUser" placeholder="Enter new user name" />
    <button @click="addUser">Add User</button>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'App',
  data() {
    return {
      users: [],
      newUser: ''
    };
  },
  mounted() {
    axios.get('http://localhost:8080/api/users')
      .then(response => {
        this.users = response.data;
      })
      .catch(error => {
        console.error('Error fetching users:', error);
      });
  },
  methods: {
    addUser() {
      axios.post('http://localhost:8080/api/users', this.newUser)
        .then(response => {
          alert(response.data);
          this.users.push(this.newUser); // 리스트에 추가
          this.newUser = '';
        })
        .catch(error => {
          console.error('Error adding user:', error);
        });
    }
  }
};
</script>
