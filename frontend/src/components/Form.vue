// stolen
<template>
  <div>
    <div id="id01" class="modal">
      <form class="modal-content animate" @submit.prevent="postToLoginService">
        <div class="imgcontainer">
          <span @click="visibility = false" class="close" title="Close Modal"
            >&times;</span
          >
        </div>

        <div class="container">
           <p>{{ msg }}</p>
          <label for="uname"><b>Username</b></label>
          <input
            v-model="login"
            type="text"
            placeholder="Enter Username"
            name="uname"
            required
          />

          <label for="psw"><b>Password</b></label>
          <input
            v-model="password"
            type="password"
            placeholder="Enter Password"
            name="psw"
            required
          />

          <button type="submit">{{ text }}</button>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Form",
  props: ["authed", "showForm", "text", "isLogin"],
  data() {
    return {
      login: "",
      password: "",
      msg: "",
    };
  },
  computed: {
    visibility: {
      get() {
        return this.modelValue;
      },
      set(value) {
        this.$emit("update:showForm", value);
        if (value == false)
          this.msg = "";
        return 0;
      },
    },
  },
  methods: {
    postToLoginService() {
      if (this.isLogin) {
        this.axios
          .post("http://localhost:10002/api/login", {
            login: this.login,
            haslo: this.password,
          })
          .then((response) => {
            this.$emit("authed", response.data.cookie, response.data.login);
            console.log(response.data);
            this.visibility = false;
          })
          .catch(() => (this.msg = "Invalid Credentials"));

        // this.visibility = false;
      } else {
        //post to /api/register
        console.log("register");
        this.axios
          .post("http://localhost:10002/api/register", {
            login: this.login,
            haslo: this.password,
          })
          .then(() => {this.visibility = false;})
          .catch(() => (this.msg = "Such a user already exists"));
      }
    },
  },
};
</script>

<style scoped>
/* Full-width input fields */
input[type="text"],
input[type="password"] {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
}

/* Set a style for all buttons */
button {
  background-color: #358aa3;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 100%;
}

button:hover {
  opacity: 0.8;
}

/* Extra styles for the cancel button */
.cancelbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #f44336;
}

/* Center the image and position the close button */
.imgcontainer {
  text-align: center;
  margin: 24px 0 12px 0;
  position: relative;
  background-color: #3f4956;
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
  background-color: #3f4956;
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* The Modal (background) */
.modal {
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0, 0, 0); /* Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #3f4956;
  margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button (x) */
.close {
  position: absolute;
  right: 25px;
  top: 0;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}

/* Add Zoom Animation */
.animate {
  -webkit-animation: animatezoom 0.6s;
  animation: animatezoom 0.6s;
}

@-webkit-keyframes animatezoom {
  from {
    -webkit-transform: scale(0);
  }
  to {
    -webkit-transform: scale(1);
  }
}

@keyframes animatezoom {
  from {
    transform: scale(0);
  }
  to {
    transform: scale(1);
  }
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
    display: block;
    float: none;
  }
  .cancelbtn {
    width: 100%;
  }
}
</style>
