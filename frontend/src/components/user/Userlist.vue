<template>
  <div>
    <Nav></Nav>
    <div class="container">
      <div class="col-12">
        <table class="table table-striped">
          <thead>
            <tr>
              <th scope="col">회원번호</th>
              <th scope="col">e-mail 아이디</th>
              <th scope="col">이름</th>
              <th scope="col">가입일</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(item,index) in userlist" v-bind:key="item">
              <td scope="row">{{index+1}}</td>
              <td>{{item.emailId}}</td>
              <td>{{item.userName}}</td>
              <td>{{item.signupDate | moment("YYYY-MM-DD")}}</td>
            </tr>

          </tbody>
          
        </table>
      </div>
    </div>
    <Footer></Footer>
  </div>
</template>

<script>
import Nav from "@/components/common/Nav.vue";
import Footer from "@/components/common/Footer.vue";
import axios from 'axios'
import moment from 'vue-moment'

export default {
  data() {
    return {
      context: "http://localhost:9000",
      userlist: []
    }
  },
  components: {
    Nav,
    Footer
  },
  created(){
        axios.get(`${this.context}/userlist`)
              .then((res)=>{
                // alert(`userlist 불러오기 ${res.data[0].userName}`)
                this.userlist = res.data;
        })
    }
};
</script>

<style scoped>
table {
  vertical-align: middle;
}
table tbody tr th,
td {
  vertical-align: middle;
}
</style>
