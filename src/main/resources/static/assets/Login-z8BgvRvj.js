import{m as C}from"./Myfetch-B8Qzcl4P.js";import{_ as j,u as S,r as p,a as R,c as B,b as e,w as o,d as l,o as E,e as i,f as I,g as _,h as L,E as N}from"./index-DXk_WzTb.js";const A="/assets/moonwolf-4CCssIRs.jpeg",M={class:"common-layout"},U={class:"aside"},Y={class:"aside-right"},q={__name:"Login",setup(z){const g=S(),{secToken:D,setSecToken:w,getSecToken:F,clearSecToken:G}=g,u=L(),y=A,b=p("fill"),x=p("top"),s=R({name:"",password:""}),h=async()=>{console.log("登录"),console.log(s.name),console.log(s.password);try{const n=await C.post("/user/login",{username:s.name,password:s.password});if(console.log(n.data.secToken),n.data.secToken===void 0){console.log("登录失败"),N.error("登录失败"),s.name="",s.password="";return}w(n.data.secToken);//! 想localstorage中存储token
u.push({path:"/home"})}catch(n){console.error(n)}},k=()=>{console.log("to reg"),u.push({name:"Register"})};return(n,t)=>{const T=l("el-image"),r=l("el-col"),V=l("el-text"),a=l("el-row"),c=l("el-form-item"),f=l("el-input"),m=l("el-button"),v=l("el-form");return E(),B("div",M,[e(a,{class:"row-bg",justify:"center"},{default:o(()=>[e(r,{span:12},{default:o(()=>[i("div",U,[e(T,{style:{width:"100%",height:"100%"},src:I(y),fit:b.value},null,8,["src","fit"])])]),_:1}),e(r,{span:12},{default:o(()=>[i("div",Y,[e(a,{class:"row-bg",justify:"center"},{default:o(()=>[e(r,{span:24},{default:o(()=>[e(v,{"label-position":x.value,"label-width":"auto",model:s,style:{"max-width":"600px"},class:"withborder"},{default:o(()=>[e(c,{style:{width:"100%"}},{default:o(()=>[e(a,{class:"row-bg",justify:"center",style:{width:"100%"}},{default:o(()=>[e(V,null,{default:o(()=>t[2]||(t[2]=[i("h2",{style:{color:"#adff2f"}}," Trust Yourself Again ",-1)])),_:1})]),_:1})]),_:1}),e(c,{label:"用户名",class:"itemxxxx","label-position":"left"},{default:o(()=>[e(f,{modelValue:s.name,"onUpdate:modelValue":t[0]||(t[0]=d=>s.name=d),"input-style":"color: #adff2f;"},null,8,["modelValue"])]),_:1}),e(c,{label:"密码",class:"itemxxxx","label-position":"left"},{default:o(()=>[e(f,{modelValue:s.password,"onUpdate:modelValue":t[1]||(t[1]=d=>s.password=d),type:"password","input-style":"color: #adff2f;"},null,8,["modelValue"])]),_:1}),e(c,{class:"itembutton"},{default:o(()=>[e(a,{class:"row-bg",justify:"center",style:{width:"100%"}},{default:o(()=>[e(m,{type:"success",plain:"",onClick:h},{default:o(()=>t[3]||(t[3]=[_("登录")])),_:1}),e(m,{type:"success",plain:"",onClick:k},{default:o(()=>t[4]||(t[4]=[_("注册")])),_:1})]),_:1})]),_:1})]),_:1},8,["label-position","model"])]),_:1})]),_:1})])]),_:1})]),_:1})])}}},K=j(q,[["__scopeId","data-v-89da92c0"]]);export{K as default};