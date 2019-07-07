<template>
  <div>
    <!--Navbar -->
    <nav class="navbar navbar-expand-lg navbar-dark elegant-color-dark">
      <router-link class="navbar-brand" to="/">VueProject</router-link>
      <div class="collapse navbar-collapse" id="navbarSupportedContent-333">
        <ul class="navbar-nav ml-auto nav-flex-icons">
          <li class="nav-item">
            <router-link class="nav-link" to="/userlist">UserList</router-link>
          </li>
          <li class="nav-item" v-if="!this.$store.state.emailId">
            <a class="nav-link" data-toggle="modal" data-target="#modalLRForm">로그인</a>
          </li>
          <li class="nav-item dropdown" v-if="this.$store.state.emailId != ''">
            <a
              class="nav-link dropdown-toggle"
              id="navbarDropdownMenuLink-333"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              <i class="fas fa-user"></i>
            </a>
            <div
              class="dropdown-menu dropdown-menu-right dropdown-default"
              aria-labelledby="navbarDropdownMenuLink-333"
            >
              <router-link class="dropdown-item" to="/mypage">마이페이지</router-link>
              <a class="dropdown-item" @click="logout">로그아웃</a>
            </div>
          </li>
        </ul>
      </div>
    </nav>
    <!--/.Navbar -->
    <!--Modal: Login / Register Form-->
    <div
      class="modal fade"
      id="modalLRForm"
      tabindex="-1"
      role="dialog"
      aria-labelledby="myModalLabel"
      aria-hidden="true"
    > 
      <div class="modal-dialog cascading-modal" role="document">
        <!--Content-->
        <div class="modal-content">
          <!--Modal cascading tabs-->
          <div class="modal-c-tabs">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs md-tabs tabs-2 light-blue" role="tablist">
              <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#panel7" role="tab" @click="erase">
                  <i class="fas fa-user mr-1"></i>
                  로그인
                </a>
              </li>
              <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#panel8" role="tab" @click="erase">
                  <i class="fas fa-user-plus mr-1"></i>
                  회원가입
                </a>
              </li>
            </ul>

            <!-- Tab panels -->
            <div class="tab-content">
              <!--Panel 7-->
              <div class="tab-pane fade in show active" id="panel7" role="tabpanel">
                <!--Body-->
                <div class="modal-body mb-1">
                  <form @submit.prevent="login">
                    <div class="md-form form-sm mb-5">
                      <i class="fas fa-envelope prefix"></i>
                      <input
                        type="email"
                        id="loginid"
                        class="form-control form-control-sm validate"
                        placeholder="e-mail ID"
                        v-model="emailId"
                      />
                    </div>

                    <div class="md-form form-sm mb-4">
                      <i class="fas fa-lock prefix"></i>
                      <input
                        type="password"
                        id="loginpass"
                        class="form-control form-control-sm validate"
                        placeholder="password"
                        v-model="password"
                      />
                    </div>
                    <div class="text-center mt-2">
                      <button class="btn btn-info">
                        Log in
                      </button>
                    </div>
                  </form>
                </div>
              </div>
              <!--/.Panel 7-->

              <!--Panel 8-->
              <div class="tab-pane fade" id="panel8" role="tabpanel">
                <!--Body-->
                <div class="modal-body">
                  <form @submit.prevent="signup">
                    <div class="md-form form-sm mb-5">
                      <i class="fas fa-envelope prefix"></i>
                      <input
                        type="email"
                        id="signupid"
                        class="form-control form-control-sm validate"
                        placeholder="e-mail ID"
                        v-model="emailId"
                      />
                    </div>

                    <div class="md-form form-sm mb-5">
                      <i class="fas fa-user prefix"></i>
                      <input
                        type="text"
                        id="signupname"
                        class="form-control form-control-sm validate"
                        placeholder="이름"
                        v-model="userName"
                      />
                    </div>

                    <div class="md-form form-sm mb-4">
                      <i class="fas fa-lock prefix"></i>
                      <input
                        type="password"
                        id="signuppass"
                        class="form-control form-control-sm validate"
                        placeholder="비밀번호"
                        v-model="password"
                      />
                    </div>

                    <div class="text-center form-sm mt-2">
                      <button class="btn btn-info">회원 가입</button>
                    </div>
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
        <!--/.Content-->
      </div>
    </div>
    <!--Modal: Login / Register Form-->
  </div>
</template>
<script>
import axios from "axios";

export default {
  data() {
    return {
      context: "http://localhost:9000",
      emailId: '',
      userName: '',
      password: ''
    }
  },
  methods: {
    erase(){
      this.emailId = ''
      this.userName = ''
      this.password = ''
    },
    signup() {
      let data = {
        emailId: this.emailId,
        userName: this.userName,
        password: this.password
      };
      let headers = {
        "Content-Type": "application/json"
      };
      axios.post(`${this.context}`, JSON.stringify(data), {headers: headers})
            .then(res => {
              alert("가입되었습니다.")
              this.emailId = ''
              this.userName = ''
              this.password = ''
            })
            .catch(e => {
              alert("회원가입을 실패하였습니다. 다시 시도해주세요")
            })
    },
    login(e) {
      let data = {
        emailId: this.emailId,
        password: this.password
      }
      let headers = {
        "Content-Type": "application/json"
      };
      axios.post(`${this.context}/login`, JSON.stringify(data), { headers: headers })
            .then(res=>{
              var target = e.target;
              target.setAttribute('data-dismiss','modal');
              target.click();
              if (res) {
                // alert(`로그인유저 : ${res.data.userName}`)
                this.$store.dispatch('LOGIN', {emailId: this.emailId, password: this.password})
                .then()
                .catch(({message}) => this.msg = message)
                this.emailId = ''
                this.password = ''
                
              } else{
                alert('id 또는 비밀번호 확인')
              }
            })
            .catch(e=> {
              alert('로그인을 실패하였습니다')
            })
    },
    logout(){
      this.$store.state.emailId = '',
      this.$store.state.password = ''
      alert('로그아웃 되었습니다')
      this.$router.push("/")
    }
  }
};
</script>
<style scoped>
</style>
