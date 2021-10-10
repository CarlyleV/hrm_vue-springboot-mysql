<template>
  <div class="mainpage">
    <!-- トップバー -->
    <div class="topBar">
      <button class="btn" @click="logout">ログアウト</button>
      <!-- <button @click="test">test</button> -->
    </div>

    <!-- サーバーエラー -->
    <div v-show="server_error">サーバー接続エラー</div>

    <!-- 新規データ画面 -->
    <div v-show="showCreate" class="create_background">
      <addData :section="section" :post="post" @closeAdd="showCreate=$event"/>
    </div>

    <div v-show="!server_error" class="content">
      <div class="create_search_bar">
      <!-- 新規データ -->
      <div>
        <button @click="showCreate=true" id="createButton">
            <i class="fa fa-plus" aria-hidden="true"></i>
        </button>
      </div>
      <!-- 検索バー -->
        <div class="searching_bar">
            <select name="search_field" id="search_field" v-model="search_field">
              <option value="" selected>-</option>
              <option v-for="(field,index) in fields.slice(0,3)" :key="'select'+index" :value="field">
                {{field_name[0][field]}}
              </option>
              <option value="other">
                備考
              </option>
            </select>
            <input type="text" name="search" id="search" v-model="search_text"> 
            <button id="clearButton" @click="clear()">×</button>
            <button @click="searchCheck(search_field,search_text)" id="searchButton"><i class="fa fa-search" aria-hidden="true"></i></button>
        </div>
      </div>

      <!-- テーブル -->
        <table>
          <!-- テーブルフィールド -->
          <thead>
            <dataField :fields="fields" :field_name="field_name" 
                        @changeOrderData="changeOrderData" @orderClear="orderClear()"/>
          </thead>

          <!-- テーブル本体 -->
          <dataBody :all="all" :post="post" :section="section" :search_prop="search_prop"/>
        </table>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import dataBody from '../components/dataBody.vue'
import dataField from '../components/dataField.vue'
import addData from '../components/addData.vue'

export default {
  name: 'Home',
  components: {
    dataBody,
    dataField,
    addData
  },
  data () {
    return{
      server_error:false,
      showCreate:false,
      //受領データ
      all:[],
      fields:[],
      post:[],
      section:[],
      field_name:[],
      //順番変更
      order:{
        field:"",
        order:""
      },
      //検索条件
      search_text:"",
      search_field:"",
      search_prop:{
        isSearch:false,
        search_text:"",
        search_field:"",
      }
    }
  },
  created(){
    const that = this;
    //サブデータAXOIS
    const getData = function(){
      return Promise.all([
        axios.get('/api/hrData/getPost'),// post取得
        axios.get('/api/hrData/getSection'),//section取得
        axios.get('/api/hrData/getField'),//field name取得
        ]).then(([post, section, field_name])=>{
          const data = {}
          data.post = post.data
          data.section = section.data
          data.field_name = field_name.data
          return Promise.resolve(data);
        }).catch((err)=>{
          console.log("post section field name"+err)})
    }
    //サブデータAXIOS AWAIT
    const asyncData = async function(){
      const result = await getData()
          that.post = result.post
          that.section = result.section
          that.field_name = result.field_name
    }
    asyncData()

    //メインデータ定時AXIOS
    window.setInterval(() => {
      if(that.order.field){
        //順番変更がある場合
        that.changeOrderData(that.order.field,that.order.order)
      }else{
        //順番変更がない場合
        that.getAll()
      }
      }, 1500);
  },
  methods: {
    logout(){
       this.$store.dispatch("logout");
      },
    //順番変更
    async changeOrderData(field, order){
      await axios.get(`/api/hrData/orderList/${field}/${order}`)
        .then((res)=>{
          this.all=res.data;
          this.order.field = field;
          this.order.order = order
          })
        .catch((err)=>{
          console.log('Error! Counld not reach the API(changeOrderData):'+ err);
          this.server_error = true
          })
    },
    //順番変更取り消し
    orderClear(){
        this.order.field = "";
        this.order.order = "";
        this.getAll()
    },
    //hr_data取得
    async getAll (){
      await axios.get('/api/hrData/getAll')
        .then((res)=>{
          this.all=res.data;
          //field取得
          this.fields=Object.keys(res.data[0])
          })
        .catch((err)=>{
          console.log('Error! Counld not reach the API(getAll):'+ err);
          this.server_error = true
          })
    },
    //検索条件チェック&検索条件作成
    searchCheck(search_field,search_text){
      if(!search_field){
        alert("カラムを選択してください")
      }else if(!search_text){
        alert("入力してください")
      }else{
        this.search_prop.search_field = search_field,
        this.search_prop.search_text = search_text,
        this.search_prop.isSearch = true      }
    },
    //検索条件クリア
    clear() {
      this.search_prop.search_field = "";
      this.search_prop.search_text = "";
      this.search_prop.isSearch = false;
      this.search_text = "";
      this.search_field = ""
    }
    // //検索
    // async search(search_field,search_text){
    //   await axios.get(`/api/hrData/search/${search_field}/${search_text}`)
    //     .then((res)=>{
    //       this.all=res.data;
    //       })
    //     .catch((err)=>{
    //       console.log('Error! Counld not reach the API(search):'+ err)
    //       })
    // },
    // //検索条件クリア
    // clear(){
    //   this.getAll()
    //   this.search_field="";
    //   this.search_text=""
    // }
  }
}
</script>

<style lang="scss" scoped>

.mainpage{
  @extend %display-flex;
  @extend %flex-direction-column;
  @extend %align-items-center;
  height:100vh;
  background:white
}

.topBar{
  width:100vw;
  height: 60px;
  border-style:solid;
  border-width:1px;
  border-color:transparent transparent black  transparent;
  @extend %display-flex;
  @extend %align-items-center;
  @extend %justify-content-flex-end;
  padding-right: 30px;
  & button{
    border-radius: 0px;
    border-style:solid;
    border-color: black;
    border-width:1px;
    font-weight: bold;
    height: 35px;
    width: 100px;
    background: white;
    @include button-hover(black,white,grey)
  }
}

.create_background{
    background-color:rgba(0, 0, 0, 0.253);
    height: 100vh;
    width: 100vw;
    position:fixed;
    z-index: 1000;
    
  }

.content{
  $create_search_bar-height:30px;

  width:90vw;
  max-width:1700px;



  & .create_search_bar{
    margin-top:30px;
    @extend %display-flex;
    @extend %justify-content-space-between;

    & #createButton{
      height:$create_search_bar-height;
      width:30px;
      border-color:black black black black;
      border-width:1px;
      background:white;
      @include button-hover(black,white,grey)
    }

    & .searching_bar{
        @extend %display-flex;
        height:$create_search_bar-height;
        & select{
          border-color:black transparent black black;
          border-width:1px
        }
        & #search{
          border-color:black transparent black black;
          border-width:1px
        }
        & #clearButton{
          border-color:black black black transparent;
          border-width:1px;
          background:white;
          @include button-hover(black,white,grey)
        }
        & #searchButton{
          border-color:black black black transparent;
          border-width:1px;
          background:white;
          @include button-hover(black,white,grey)
        }
  }

}


  table{
    margin-top:30px;
    width:100%;
    text-align:center;
    border-collapse: collapse;
    border-spacing: 0;
  }

}
</style>
