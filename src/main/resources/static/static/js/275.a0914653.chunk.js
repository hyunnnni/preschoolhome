"use strict";(self.webpackChunkkindergarden=self.webpackChunkkindergarden||[]).push([[275],{6354:(n,t,e)=>{e.d(t,{Nm:()=>a,Zt:()=>l,gp:()=>c,iI:()=>s,sz:()=>d});var r=e(4880),o=e(3014);const i="".concat(o.L,"/api/full"),a=async n=>{let{tno:t,successFn:e,failFn:o,errorFn:a}=n;try{const n=await r.Z.get("".concat(i,"?iFullNotice=").concat(t));"2"===n.status.toString().charAt(0)?e(n.data):o("\uc790\ub8cc \ud638\ucd9c \uc5d0\ub7ec\uc785\ub2c8\ub2e4.")}catch(c){a(c)}},c=async n=>{let{page:t,successFn:e,failFn:o,errorFn:a}=n;try{const n=await r.Z.get("".concat(i,"/listall?page=").concat(t));"2"===n.status.toString().charAt(0)?e(n.data):o("\uc790\ub8cc \ud638\ucd9c \uc5d0\ub7ec\uc785\ub2c8\ub2e4.")}catch(c){a(c)}},s=async n=>{let{postData:t,successFn:e,failFn:o,errorFn:a}=n;try{const n=await r.Z.post("".concat(i),t);"2"===n.status.toString().charAt(0)?e(n.data):o("\ub4f1\ub85d \uc5d0\ub7ec\uc785\ub2c8\ub2e4.")}catch(c){a(c)}},l=async n=>{let{successFn:t,failFn:e,errorFn:o}=n;try{const n=await r.Z.delete("".concat(i));"2"===n.status.toString().charAt(0)?t():e("\uc0ad\uc81c \uc5d0\ub7ec\uc785\ub2c8\ub2e4.")}catch(a){o(a)}},d=async n=>{let{data:t,successFn:e,failFn:o,errorFn:a}=n;try{const n={headers:{"Content-Type":"multipart/form-data"}},a=await r.Z.put("".concat(i),t,n);"2"===a.status.toString().charAt(0)?e(a.data):o("\uc218\uc815 \uc5d0\ub7ec\uc785\ub2c8\ub2e4.")}catch(c){a(c)}}},1763:(n,t,e)=>{e.r(t),e.d(t,{default:()=>b,obj:()=>f});var r=e(2106),o=e(2791),i=e(7832),a=e.n(i),c=(e(5580),e(1087)),s=e(7689),l=e(6354),d=e(3955),h=e(8188),u=e(3014),g=e(184);const p="".concat(u.b,"/pic/fullnotice"),f={pics:[""],dto:{ifullNotice:0,fullTitle:"",fullContents:"",fullNoticeFix:0,iteacher:0}},b=()=>{(0,c.lr)();const[n,t]=(0,o.useState)(0),[e,i]=(0,o.useState)(!1),[u,b]=(0,o.useState)(f),[x,m]=(0,o.useState)([]),{tno:y}=(0,s.UO)(),Z=(0,s.s0)(),k=n=>{b(n);const e=n.pics,r=e.map(((n,t)=>({original:"".concat(p,"/").concat(y,"/")+n,thumbnail:"".concat(p,"/").concat(y,"/")+n})));m((n=>[...n,...r])),t(e.length)},j=n=>{},v=n=>{};return(0,o.useEffect)((()=>{(0,l.Nm)({tno:y,successFn:k,failFn:j,errorFn:v})}),[y]),(0,g.jsxs)(g.Fragment,{children:[(0,g.jsx)(d.V1,{children:"\uc720\uce58\uc6d0 \uc18c\uc2dd"}),(0,g.jsxs)("div",{style:{display:"flex",flexDirection:"column",alignItems:"center",marginTop:30},children:[(0,g.jsxs)("div",{style:{borderTop:"1.5px solid #00876D",borderBottom:"1.5px solid #00876D",width:"100%",background:"white",textAlign:"center",paddingTop:20,justifyContent:"center"},children:[(0,g.jsxs)("div",{style:{display:"flex",justifyContent:"space-between",alignItems:"center"},children:[(0,g.jsx)("div",{style:{margin:"auto"},children:(0,g.jsx)("p",{style:{margin:0,fontSize:27},children:u.fullTitle})}),(0,g.jsx)("p",{style:{marginRight:20,fontSize:15,color:"#999"},children:u.createdAt})]}),(0,g.jsx)("div",{style:{borderTop:"1.5px solid #DDDDDD",width:"100%",textAlign:"center",marginTop:20},children:(0,g.jsx)("div",{style:{margin:40,maxWidth:500,display:"inline-block"},children:(0,g.jsx)(a(),{items:x,thumbnailPosition:"left",slideInterval:1700})},n)}),(0,g.jsx)("p",{style:{margin:30,textAlign:"center",fontSize:20},children:u.fullContents})]}),(0,g.jsxs)("div",{style:{width:"100%",display:"flex",justifyContent:"flex-end",marginTop:20},children:[(0,g.jsx)("div",{style:{marginRight:10},children:(0,g.jsx)(c.rU,{to:"/notice",children:(0,g.jsx)(h.lk,{children:"\ubaa9\ub85d\ubcf4\uae30"})})}),(0,g.jsx)("div",{style:{marginRight:10},children:(0,g.jsx)(c.rU,{to:{pathname:"/notice/modify/".concat(y),state:{detailData:u}},children:(0,g.jsx)(h.f4,{children:"\uc218\uc815"})})}),(0,g.jsx)("div",{children:(0,g.jsx)(h.s2,{onClick:()=>{i(!0)},children:"\uc0ad\uc81c"})})]})]}),(0,g.jsx)(r.default,{title:"\uc815\ub9d0 \uc0ad\uc81c\ud560\uae4c\uc694?",open:e,onOk:async()=>{try{await(0,l.Zt)({tno:y,successFn:()=>{Z("/notice")},failFn:n=>{console.error("\uc0ad\uc81c\uc2e4\ud328:",n)},errorFn:n=>{console.error("\uc0ad\uc81c \uc5d0\ub7ec:",n)}}),i(!1)}catch(n){console.error("\uc0ad\uc81c \ucc98\ub9ac \uc911 \uc5d0\ub7ec \ubc1c\uc0dd:",n)}},onCancel:()=>{i(!1)},okText:"\ud655\uc778",cancelText:"\ucde8\uc18c",children:(0,g.jsx)("p",{children:"\uc0ad\uc81c\ub41c \ub0b4\uc6a9\uc740 \ubcf5\uad6c\ud560 \uc218 \uc5c6\uc2b5\ub2c8\ub2e4."})})]})}},8188:(n,t,e)=>{e.d(t,{Kj:()=>j,Lr:()=>T,f4:()=>D,i5:()=>S,iI:()=>F,lk:()=>v,o4:()=>w,s2:()=>O,sd:()=>C,x0:()=>A});var r,o,i,a,c,s,l,d,h,u,g,p,f,b=e(168),x=e(7323),m=e(3955),y=e(2106);const Z={fontSize:"1.8rem",fontFamily:'"KOTRAHOPE", "Pretendard", sans-serif',background:"#fff",border:0,padding:"1rem 1.5rem",borderRadius:"1rem",cursor:"pointer",minWidth:"8rem"},k={boxShadow:"0px 0px 10px 0px rgba(0, 0, 0, 0.15)"},j=x.Z.div(r||(r=(0,b.Z)(["\n  position: relative;\n  display: flex;\n  gap: 1rem;\n  align-items: center;\n"]))),v=x.Z.button(o||(o=(0,b.Z)(["\n  ","\n  background: ",";\n  color: ",";\n  transition: 0.2s;\n  :hover {\n    ","\n  }\n"])),Z,m.O9.greenLight,m.O9.greenDeep,k),F=x.Z.button(i||(i=(0,b.Z)(["\n  ","\n  background: ",";\n  color: ",";\n  transition: 0.2s;\n  :hover {\n    ","\n  }\n"])),Z,m.O9.orangeLight,m.O9.orangeDeep,k),O=x.Z.button(a||(a=(0,b.Z)(["\n  ","\n  background: ",";\n  color: ",";\n  :hover {\n    ","\n  }\n"])),Z,m.O9.pinkLight,m.O9.pinkDeep,k),D=x.Z.button(c||(c=(0,b.Z)(["\n  ","\n  background: ",";\n  color: ",";\n  transition: 0.2s;\n  :hover {\n    ","\n  }\n"])),Z,m.O9.blueLight,m.O9.blueDeep,k),w=(x.Z.button(s||(s=(0,b.Z)(["\n  ","\n  background: ",";\n  color: ",";\n  transition: 0.2s;\n  :hover {\n    ","\n  }\n"])),Z,m.O9.redLight,m.O9.redDeep,k),x.Z.button(l||(l=(0,b.Z)(["\n  ","\n  background: ",";\n  color: ",";\n  transition: 0.2s;\n  :hover {\n    ","\n  }\n"])),Z,m.O9.purpleLight,m.O9.purpleDeep,k)),S=x.Z.button(d||(d=(0,b.Z)(["\n  ","\n  background: ",";\n  color: ",";\n  transition: 0.2s;\n  :hover {\n    ","\n  }\n"])),Z,m.O9.grayLight,m.O9.grayDeep,m.AF),T=(x.Z.button(h||(h=(0,b.Z)(["\n  ","\n  background: ",";\n  color: ",";\n  transition: 0.1s;\n  :hover {\n    ","\n  }\n"])),Z,m.O9.grayDeep,m.O9.white,k),x.Z.button(u||(u=(0,b.Z)(["\n  ","\n  background: #ffadc7;\n  color: #fe77a2;\n  transition: 0.1s;\n  :hover {\n    ","\n  }\n"])),Z,k)),A=x.Z.button(g||(g=(0,b.Z)(["\n  ","\n  background: #ffcc58;\n  color: #db8400;\n  transition: 0.1s;\n  :hover {\n    ","\n  }\n"])),Z,k),C=x.Z.button(p||(p=(0,b.Z)(["\n  ","\n  position: absolute;\n  right: -2.5rem;\n  top: 2.5rem;\n  padding: 1rem 0.5rem;\n  font-size: 1.6rem;\n  border-radius: 0 0.5rem 0.5rem 0;\n  min-width: auto;\n  width: 2.5rem;\n  height: 7rem;\n  background: ",";\n  color: ",";\n  z-index: 11;\n  word-break: break-all;\n  line-height: 1;\n"])),Z,m.O9.greenLight,m.O9.greenDeep);(0,x.Z)(y.default)(f||(f=(0,b.Z)(["\n  ant-btn {\n    color: red;\n  }\n"])))}}]);
//# sourceMappingURL=275.a0914653.chunk.js.map