//package com.example.merlebleu2.controller;
//
//import lombok.Value;
//import org.springframework.ui.Model;
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Controller
//@RequestMapping(value = "/merlebleu")
//public class MerleBleuController {
//
//
//    //메인페이지 경로
//
//    //===============================================================================================================//
//
//
//
////    //상품 목록 outer 경로
////    @GetMapping(value = "/search_outer")
////    public String merlbleusearchouter(Model model) {
////        return "MerleBleu/prod/prodList/search_outer.html";
////    }
//
//
//    //상품 목록 top 경로
//
////    @GetMapping(value = "/search_top")
////    public String marlbleusearchtop(Model model) {
////        return "MerleBleu/prod/prodList/search_top.html";
////    }
////
////
////    //상품 목록  skirt 경로
////
////    @GetMapping(value = "/search_skirt")
////    public String marlbleusearchskirt(Model model) {
////        return "MerleBleu/prod/prodList/search_skirt.html";
////    }
////
////
//////상품 목록 pants 경로
////
////    @GetMapping(value = "/search_pants")
////    public String marlbleusearchpants(Model model) {
////        return "MerleBleu/prod/prodList/search_pants.html";
////    }
////
////    //상품 목록 onepiece 경로
////
////    @GetMapping(value = "/search_onepiece")
////    public String marlbleusearchonepiece(Model model) { return  "MerleBleu/prod/prodList/search_onepiece.html";}
////
////
////    //상품 목록 stuff 경로
////    @GetMapping(value = "/search_stuff")
////    public String merlbleusearchstuff(Model model) {
////        return "MerleBleu/prod/prodList/search_stuff.html";
////    }
//
//
//
//    //=========================================================================================================================//
//
//    //상품 목록 outer-jacket 경로
//
//    @GetMapping(value = "/outer-jacket")
//    public String marlbleuouterjacket(Model model) { return  "MerleBleu/prod/prodDetail/outer-jacket.html";}
//
//    //상품 목록 outer-jumper 경로
//
//    @GetMapping(value = "/outer-jumper")
//    public String marlbleuouterjumper(Model model) { return  "MerleBleu/prod/prodDetail/outer-jumper.html";}
//
//    //상품 목록 outer-padding 경로
//
//    @GetMapping(value = "/outer-padding")
//    public String marlbleuouterpadding(Model model) { return  "MerleBleu/prod/prodDetail/outer-padding.html";}
//
//    //상품 목록 outer-cardigan 경로
//
//    @GetMapping(value = "/outer-cardigan")
//    public String marlbleuoutercardigan(Model model) { return  "MerleBleu/prod/prodDetail/outer-cardigan.html";}
//
//    //상품 목록 outer-jacket 경로
//
//    @GetMapping(value = "/outer-coat")
//    public String marlbleuoutercoat(Model model) { return  "MerleBleu/prod/prodDetail/outer-coat.html";}
//
//    //상품 목록 outer-jacket 경로
//
//    @GetMapping(value = "/outer-mustang")
//    public String marlbleuoutermustang(Model model) { return  "MerleBleu/prod/prodDetail/outer-mustang.html";}
//
//    //상품 목록 outer-jacket 경로
//
//    @GetMapping(value = "/outer-trench-coat")
//    public String marlbleuoutertrenchcoat(Model model) { return  "MerleBleu/prod/prodDetail/outer-trench-coat.html";}
//
//    //=========================================================================================================================//
//
//
//    //상품 목록 top-neat 경로
//
//    @GetMapping(value = "/top-neat")
//    public String marlbleutopneat(Model model) { return  "MerleBleu/prod/prodDetail/top-neat.html";}
//
//    //상품 목록 top-shirt 경로
//
//    @GetMapping(value = "/top-shirt")
//    public String marlbleutopshirt(Model model) { return  "MerleBleu/prod/prodDetail/top-shirt.html";}
//
//    //상품 목록 top-tshirt 경로
//
//    @GetMapping(value = "/top-tshirt")
//    public String marlbleutoptshirt(Model model) { return  "MerleBleu/prod/prodDetail/top-tshirt.html";}
//
//    //상품 목록 top-sleeveless 경로
//
//    @GetMapping(value = "/top-sleeveless")
//    public String marlbleutopsleeveless(Model model) { return  "MerleBleu/prod/prodDetail/top-sleeveless.html";}
//
//    //=================================================================================================================
//
//
//    //상품 목록 skirt-denim 경로
//
//    @GetMapping(value = "/skirt-denim")
//    public String marlbleuskirtdenim(Model model) { return  "MerleBleu/prod/prodDetail/skirt-denim.html";}
//
//    //상품 목록 skirt-flare경로
//
//    @GetMapping(value = "/skirt-flare")
//    public String marlbleuskirtflare(Model model) { return  "MerleBleu/prod/prodDetail/skirt-flare.html";}
//
//    //상품 목록 skirt-denim 경로
//
//    @GetMapping(value = "/skirt-mini")
//    public String marlbleuskirtmini(Model model) { return  "MerleBleu/prod/prodDetail/skirt-mini.html";}
//
//    //상품 목록 skirt-pencil 경로
//
//    @GetMapping(value = "/skirt-pencil")
//    public String marlbleuskirtpencil(Model model) { return  "MerleBleu/prod/prodDetail/skirt-pencil.html";}
//
//
////===================================================================================================================
//
//    //상품 목록 onepiece-long 경로
//
//    @GetMapping(value = "/onepiece-long")
//    public String marlbleuonepiecelong(Model model) { return  "MerleBleu/prod/prodDetail/onepiece-long.html";}
//
//    //상품 목록 onepiece-midi 경로
//
//    @GetMapping(value = "/onepiece-midi")
//    public String marlbleuonepiecemidi(Model model) { return  "MerleBleu/prod/prodDetail/onepiece-midi.html";}
//
//    //상품 목록 onepiece-mini 경로
//
//    @GetMapping(value = "/onepiece-mini")
//    public String marlbleuonepiecemini(Model model) { return  "MerleBleu/prod/prodDetail/onepiece-mini.html";}
//
//
////===================================================================================================================
//
//
//    //상품 목록 pants-casual 경로
//
//    @GetMapping(value = "/pants-casual")
//    public String marlbleupantscasual(Model model) { return  "MerleBleu/prod/prodDetail/pants-casual.html";}
//
//    //상품 목록 pants-denim 경로
//
//    @GetMapping(value = "/pants-denim")
//    public String marlbleupantsdenim(Model model) { return  "MerleBleu/prod/prodDetail/pants-denim.html";}
//
//    //상품 목록 pants-formal 경로
//
//    @GetMapping(value = "/pants-formal")
//    public String marlbleupantsformal(Model model) { return  "MerleBleu/prod/prodDetail/pants-formal.html";}
//
//    //상품 목록 pants-shorts 경로
//
//    @GetMapping(value = "/pants-shorts")
//    public String marlbleupantsshorts(Model model) { return  "MerleBleu/prod/prodDetail/pants-shorts.html";}
//
//
//
//    //==============================================================================================================
//
//
//    //상품 목록 stuff-bag 경로
//
//    @GetMapping(value = "/stuff-bag")
//    public String marlbleustuffbag(Model model) { return  "MerleBleu/prod/prodDetail/stuff-bag.html";}
//
//    //상품 목록 stuff-belt 경로
//
//    @GetMapping(value = "/stuff-belt")
//    public String marlbleustuffbelt(Model model) { return  "MerleBleu/prod/prodDetail/stuff-belt.html";}
//
//    //상품 목록 stuff-bag 경로
//
//    @GetMapping(value = "/stuff-etc")
//    public String marlbleustuffetc(Model model) { return  "MerleBleu/prod/prodDetail/stuff-etc.html";}
//
//    //상품 목록 stuff-hat 경로
//
//    @GetMapping(value = "/stuff-hat")
//    public String marlbleustuffhat(Model model) { return  "MerleBleu/prod/prodDetail/stuff-hat.html";}
//
//    //상품 목록 stuff-scarf 경로
//
//    @GetMapping(value = "/stuff-scarf")
//    public String marlbleustuffscarf(Model model) { return  "MerleBleu/prod/prodDetail/stuff-scarf.html";}
//
//    //상품 목록 stuff-shoes 경로
//
//    @GetMapping(value = "/stuff-shoes")
//    public String marlbleustuffshoes(Model model) { return  "MerleBleu/prod/prodDetail/stuff-shoes.html";}
//
//    //상품 목록 stuff-socks 경로
//
//    @GetMapping(value = "/stuff-socks")
//    public String marlbleustuffsocks(Model model) { return  "MerleBleu/prod/prodDetail/stuff-socks.html";}
//
//
//    //=============================================================================================================
//
//
//
//
//
//
//
//
//
//
//
//
//
//}