"use strict";(self.webpackChunkkindergarden=self.webpackChunkkindergarden||[]).push([[329],{3329:(e,a,c)=>{c.r(a),c.d(a,{default:()=>y});var i=c(6834),s=c(2791),n=c(5847),t=(c(3955),c(3353)),r=c(4155),l=c(7689),o=c(1087),d=c(724),h=(c(8199),c(184));const g=e=>{let{listData:a,year:c,ikid:i,iclass:s,page:n}=e;const{loginState:r,isLogin:l,isParentLogin:g}=(0,d.Z)();return(0,h.jsx)(t.ti,{children:(0,h.jsx)(t.lt,{children:Array.isArray(a)&&0===a[0].inotice?(0,h.jsx)("div",{style:{textAlign:"center",width:"100%",marginTop:"5rem"},children:"\uc870\ud68c\ub418\ub294 \uc815\ubcf4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4."}):Array.isArray(a)&&a.map((e=>{return(0,h.jsx)(t.Ex,{className:1===e.noticeCheck?"notice":"",children:(0,h.jsxs)(o.rU,{to:l?"/ind/details/".concat(e.inotice,"?year=").concat(c,"&page=").concat(n,"&iclass=").concat(s):"/ind/details/".concat(e.inotice,"?year=").concat(c,"&page=").concat(n,"&ikid=").concat(i),children:[(0,h.jsxs)(t.qQ,{children:[(0,h.jsxs)(t.On,{children:[(a=e.iclass,1===a?"\ubb34\uad81\ud654\ubc18":2===a?"\ud574\ubc14\ub77c\uae30\ubc18":3===a?"\uc7a5\ubbf8\ubc18":-1===a?"\ud1f4\uc18c":-2===a?"\uc878\uc5c5":"")," ",e.kidNm]}),(0,h.jsxs)(t.BD,{children:[1===e.noticeCheck?(0,h.jsx)("img",{src:"/images/common/warning_icon.svg",alt:"file"}):null,(0,h.jsx)("span",{children:e.createdAt.split(" ")[0]}),(0,h.jsx)("b",{children:e.noticeTitle})]}),(0,h.jsxs)(t.mT,{children:[1===e.picCheck?(0,h.jsx)("img",{src:"/images/common/file_icon.svg",alt:"file"}):null,1===e.cmtCheck?(0,h.jsx)("img",{src:"/images/common/chat_icon.svg",alt:"file"}):null]})]}),(0,h.jsx)(t.Bq,{children:(0,h.jsx)("span",{children:e.noticeContents})})]})},e.inotice);var a}))})})};var u=c(3359),m=c(1717),p=c(747),x=c(5816),j=c(2603),k=c(8188);const f=[{inotice:0,noticeTitle:"",noticeContents:"",kidNm:"",iclass:0,picCheck:0,cmtCheck:0,noticeCheck:0,createdAt:""}],y=()=>{const e=(0,l.s0)(),{pathname:a,search:c}=(0,l.TH)(),[y,C]=(0,o.lr)(),[T,v]=(0,s.useState)(f),[L,S]=(0,s.useState)(3),[b,A]=(0,s.useState)(0),N=((new Date).getFullYear(),y.get("year")),w=y.get("ikid"),F=y.get("page"),V=y.get("iclass"),Z=y.get("searchValue")||"",{loginState:D,isLogin:q,isParentLogin:_,isTeacherLogin:B,isAdminLogin:O,isAccept:U}=(0,d.Z)(),z=D.kidList,[E,P]=(0,s.useState)(""),[H,Q]=(0,s.useState)(""),[Y,G]=(0,s.useState)(!1),[I,J]=(0,s.useState)();(0,s.useEffect)((()=>{if(_){if("0"===w)return P("\uc5f0\uacb0 \uc624\ub958"),Q("\uc5f0\uacb0\ub41c \uc6d0\uc0dd \uc815\ubcf4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4."),J(-1),void G(!0);if(!U)return;(0,u.Dq)({page:F,year:N,ikid:w,fromTo:L,search:"",errorFn:M,successFn:K})}else if(q){if(!(B&&V==D.iclass||O))return P("\uc811\uadfc \uc81c\ud55c"),Q("\ub2f4\ub2f9\ud55c \ubc18 \uc54c\ub9bc\uc7a5\ub9cc \uc5f4\ub78c \uac00\ub2a5\ud569\ub2c8\ub2e4."),J(-1),void G(!0);(0,u.yu)({page:F,year:N,iclass:V,fromTo:L,search:Z,errorFn:M,successFn:K})}else G(!0),P("\ud68c\uc6d0 \uc804\uc6a9 \ud398\uc774\uc9c0"),Q("\ub85c\uadf8\uc778 \ud68c\uc6d0\ub9cc \uc811\uadfc \uac00\ub2a5\ud569\ub2c8\ub2e4."),J("/login")}),[N,w,V,F,L]);const K=e=>{v(e.list),A(e.noticeCnt)},M=e=>{v(f)},R=a=>{const c=a.target.value,i=q?"/ind?year=".concat(N,"&page=1&iclass=").concat(V,"&searchValue=").concat(Z,"&fromTo="):"/ind?year=".concat(N,"&page=1&ikid=").concat(w,"&searchValue=").concat(Z,"&fromTo="),s=q&&"teacher"==c?1:q&&"parent"==c?0:_&&"parent"==c?1:_&&"teacher"==c?0:3;e(i+s,{state:{writer:c}}),S(s)},W=e=>{v(f)};return console.log(D),(0,h.jsxs)(t.Nm,{children:[(0,h.jsx)(m.Z,{isOpen:Y,handleOk:()=>{G(!1),I&&e(I)},title:E,subTitle:H}),(0,h.jsxs)(t.eN,{children:[(0,h.jsx)(o.rU,{to:a+c,className:"active",children:"\uc54c\ub9bc\uc7a5"}),(0,h.jsx)(o.rU,{to:"/ind/album".concat(c),children:"\ucd94\uc5b5\uc568\ubc94"})]}),(0,h.jsxs)(r.BV,{className:"ind-btn-wrap",children:[(0,h.jsxs)(t.ih,{fromTo:L,isLogin:q,children:[(0,h.jsxs)("button",{onClick:e=>R(e),value:"teacher",className:"teacher",children:[q?"\ub0b4\uac00 \uc4f4":"\ub0b4\uac00 \ubc1b\uc740"," \uae00"]}),(0,h.jsxs)("button",{onClick:e=>R(e),value:"parent",className:"parent",children:[q?"\ub0b4\uac00 \ubc1b\uc740":"\ub0b4\uac00 \uc4f4"," \uae00"]}),3!=L?(0,h.jsx)("button",{onClick:e=>R(e),value:"all",children:"\ubaa8\uc544\ubcf4\uae30"}):null]}),(0,h.jsxs)(r.hs,{children:[q?(0,h.jsx)(x.Z,{iclass:V,indList:T,year:N,page:F}):(0,h.jsx)(p.Z,{ikidList:z,indList:T,ikid:w,year:N,page:F,isAccept:U}),(0,h.jsx)(j.Z,{placeholder:"\uc774\ub984\uc744 \uc785\ub825\ud558\uc138\uc694.",allowClear:!0,onSearch:e=>{(e=>{_?((0,u.Dq)({page:1,year:N,ikid:w,fromTo:L,search:e,errorFn:W,successFn:K}),C({page:1,year:N,ikid:w,fromTo:L,searchValue:e})):q&&((0,u.yu)({page:1,year:N,iclass:V,fromTo:L,search:e,errorFn:W,successFn:K}),C({page:1,year:N,iclass:V,fromTo:L,searchValue:e}))})(e)},size:"large"}),(0,h.jsx)(k.lk,{onClick:()=>{e("/ind/write")},className:"btn",children:"\uae00\uc4f0\uae30"})]})]}),(0,h.jsx)(g,{listData:T,year:N,ikid:w,iclass:V,page:F}),(0,h.jsx)(n.V_,{children:(0,h.jsx)(i.Z,{defaultCurrent:F,total:b,pageSize:12,onChange:(a,c)=>{e(q?"/ind?year=".concat(N,"&page=").concat(a,"&iclass=").concat(V,"&fromTo=").concat(L):"/ind?year=".concat(N,"&page=").concat(a,"&ikid=").concat(w,"&fromTo=").concat(L))}})})]})}}}]);
//# sourceMappingURL=329.97db8bbc.chunk.js.map