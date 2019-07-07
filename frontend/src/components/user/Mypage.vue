<template>
  <div>
    <Nav></Nav>
    <div id="mypage" class="container">
      <div class="table user-detail flex md6">
        <br />
        <table class="table first-td-padding">
          <tr>
            <td class="first-td">
              <li>아이디</li>
            </td>
            <td>{{loginUser.emailId}}</td>
          </tr>
          <tr>
            <td class="first-td">
              <li>비밀번호</li>
            </td>
            <td>
              <button class="btn btn-primary" data-toggle="modal" data-target="#modipw">수정</button>
            </td>
          </tr>
          <tr>
            <td class="first-td">
              <li>이름</li>
            </td>
            <td>{{loginUser.userName}}</td>
          </tr>
          <tr>
            <td class="first-td">
              <li>프로필</li>
            </td>
            <td>
              <div>
                <img src="../../assets/default-profile-icon.jpg" alt="profile" />
              </div>
              <div class="filebox">
                <label for="uploadBtn" class="btn">프로필 등록</label>
                <input type="file" name="uploadFile" id="uploadBtn" class="uploadBtn" />
              </div>
            </td>
          </tr>
        </table>
      </div>
      <div style="text-align: right;">
        <button class="btn btn-danger" data-toggle="modal" data-target="#withdraw">회원 탈퇴</button>
      </div>
    </div>

    <!-- 비밀번호 수정 modal -->
    <div
      class="modal fade"
      id="modipw"
      tabindex="-1"
      role="dialog"
      aria-labelledby="myModalLabel"
      aria-hidden="true">
      <div class="modal-dialog modal-notify modal-primary" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <p class="heading">비밀번호 수정</p>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true" class="white-text">&times;</span>
            </button>
          </div>
          <!-- modal body -->
          <div class="modal-body">
            <form @submit.prevent="modipass">
              <label for="newPass">새 비밀번호</label>
            <input id="newPass" class="form-control" type="text" v-model="newPass" placeholder="새 비밀번호"></input>
          <button class="btn float-right" data-dismiss="modal">취소</button>
          <button type="submit" class="btn btn-success float-right" @click="close">수정하기</button>
        </form>
          </div>
        </div>
      </div>
    </div>

    <!-- 회원탈퇴 modal-->
    <div
      class="modal fade"
      id="withdraw"
      tabindex="-1"
      role="dialog"
      aria-labelledby="myModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog modal-notify modal-danger" role="document">
        <!--Content-->
        <div class="modal-content">
          <!--Header-->
          <div class="modal-header">
            <p class="heading">회원 탈퇴</p>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true" class="white-text">&times;</span>
            </button>
          </div>
          <!--Body-->
          <div class="modal-body">
            <div class="row">
              <p id="withdrawtext">탈퇴하시겠습니까?</p>
            </div>
          </div>
          <!--Footer-->
          <div class="modal-footer justify-content-center">
            <button type="button" class="btn btn-danger" @click="withdraw" data-dismiss="modal">네</button>
            <button type="button" class="btn btn-outline-danger" data-dismiss="modal">아니오</button>
          </div>
        </div>
        <!--/.Content-->
      </div>
    </div>
    <!-- 회원 탈퇴 modal-->

    <Footer></Footer>
  </div>
</template>

<script>
import Nav from "@/components/common/Nav.vue";
import Footer from "@/components/common/Footer.vue";
import axios from "axios";
import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue/dist/bootstrap-vue.css";

export default {
  data() {
    return {
      context: "http://localhost:9000",
      loginId: this.$store.state.emailId,
      password: "",
      loginUser: "",
      newPass: ""
    };
  },
  components: {
    Nav,
    Footer
  },
  methods: {
    close(){
      this.$emit('close')
    },
    modipass(e) {
      let data = {
        emailId: this.loginId,
        password: this.newPass
      };
      let headers = {
        "Content-Type": "application/json",
        'Authorization': 'JWT fefege..'
      };
      axios
        .put(`${this.context}/modi`, JSON.stringify(data), { headers: headers })
        .then(res => {
          alert("비밀번호가 수정되었습니다");
          this.newPass = "";
          var target = e.target;
              target.setAttribute('data-dismiss','modal');
              target.click();
        });
    },
    withdraw() {
      axios.delete(`${this.context}/delete/${this.loginId}`).then(res => {
        alert("탈퇴완료");
        this.$store.state.emailId = "";
        this.$store.state.password = "";
        this.$router.push("/");
      });
    }
  },
  created() {
    axios.get(`${this.context}/mypage/${this.loginId}`).then(res => {
      this.loginUser = res.data;
    });
  }
};
</script>

<style scoped>
table td {
  vertical-align: middle;
}
table th .filebox label {
  display: inline-block;
  padding: 0.5em 0.75em;
  color: lightgray;
  font-size: inherit;
  line-height: normal;
  vertical-align: middle;
  cursor: pointer;
  border-radius: 0.25em;
  padding-left: 22px;
  padding-right: 22px;
  margin-left: 2px;
  margin-right: 0px;
}

.filebox input[type="file"] {
  position: absolute;
  width: 1px;
  height: 1px;
  padding: 0;
  margin: -1px;
  overflow: hidden;
  clip: rect(0, 0, 0, 0);
}

</style>
