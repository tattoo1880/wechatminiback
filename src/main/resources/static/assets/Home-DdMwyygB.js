import{u as w,h as B,r as M,d as n,o as i,i as V,w as o,b as e,e as l,_ as b,c as E,F as R}from"./index-BElxOaZ8.js";/* empty css                                                 */const z={__name:"Myheader",setup(m){const _=w(),{secToken:p,setSecToken:r,getSecToken:d,clearSecToken:c}=_,s=B(),x=M("1"),k=(f,t)=>{console.log(f,t)},g=()=>{s.push({name:"MyList"})},y=()=>{s.push({name:"MyX"})},v=()=>{//!清空localStorage
c(),s.push({name:"Login"})};return(f,t)=>{const S=n("ElementPlus"),T=n("el-icon"),a=n("el-text"),u=n("el-menu-item"),C=n("el-sub-menu"),F=n("el-menu");return i(),V(F,{"default-active":x.value,class:"el-menu-demo",mode:"horizontal",ellipsis:!1,onSelect:k},{default:o(()=>[e(u,{index:"0"},{default:o(()=>[e(T,{style:{color:"#adff2f"},size:"200"},{default:o(()=>[e(S)]),_:1}),e(a,{style:{color:"#adff2f"}},{default:o(()=>t[0]||(t[0]=[l("strong",null,"Trust yourself once again",-1)])),_:1})]),_:1}),e(u,{index:"1",onClick:g},{default:o(()=>[e(a,{style:{color:"#adff2f"}},{default:o(()=>t[1]||(t[1]=[l("strong",null,"Status",-1)])),_:1})]),_:1}),e(C,{index:"2"},{title:o(()=>[e(a,{style:{color:"#adff2f"}},{default:o(()=>t[2]||(t[2]=[l("strong",null,"MyAll",-1)])),_:1})]),default:o(()=>[e(u,{index:"2-1",onClick:y},{default:o(()=>[e(a,{style:{color:"#adff2f"}},{default:o(()=>t[3]||(t[3]=[l("strong",null,"添加X视频",-1)])),_:1})]),_:1}),e(u,{index:"2-3",onClick:v},{default:o(()=>[e(a,{style:{color:"#adff2f"}},{default:o(()=>t[4]||(t[4]=[l("strong",null,"登出",-1)])),_:1})]),_:1})]),_:1})]),_:1},8,["default-active"])}}},A={__name:"Home",setup(m){return(_,p)=>{const r=n("RouterView"),d=n("star-filled"),c=n("el-icon"),s=n("el-divider");return i(),E(R,null,[l("div",null,[e(z)]),l("div",null,[e(r)]),e(s,null,{default:o(()=>[e(c,null,{default:o(()=>[e(d,{color:"#ADFF2F"})]),_:1})]),_:1})],64)}}},L=b(A,[["__scopeId","data-v-8358dd19"]]);export{L as default};
