package com.aiolos.commons.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 支付取消/失败各种语言的消息提示和建议
 * @author Aiolos
 * @date 2021/12/4 9:03 下午
 */
public enum PaymentExceptionEnum {

    UNPAID {
        @Override
        public PaymentExceptionInfo getEnum() {
            PaymentExceptionInfo p = new PaymentExceptionInfo();
            p.setCode(10000);
            p.setCnMsg("因未知原因，该笔订单未完成交易。");
            p.setCnSuggestion("你可以在“账号＞订单＞未付款”中查看订单，或点击“View Order”。也可以点击\"Pay again\"重新支付。");
            p.setEnMsg("The order has not completed the transaction due to unknown reasons.");
            p.setEnSuggestion("You can enter “My Account>My Orders>Pendingpayment” or click \"View Order\" to check out your order. You can also click \"Pay again \" to re-pay.");
            p.setEsMsg("el pedido no ha completado la transacción por motivos");
            p.setEsSuggestion("puedes entrar¨mi cuenta>mis pedidos>pago pediente¨o click ¨ver el pedido¨a chequear tu pedido.también puedes click ¨paga otra vez¨ a pagar de nuevo.");
            p.setFrMsg("La commande n'a pas terminé la transaction pour des raisons inconnues.");
            p.setFrSuggestion("Vous pouvez entrer \"Mon compte>Mes commandes>Paiement en attente\" ou cliquer sur \"Voir la commande\" pour vérifier votre commande. Vous pouvez également cliquer sur « Payer à nouveau » pour re-payer.");
            p.setDeMsg("Aus unbekannten Gründen wurde die Bestellung nicht abgeschlossen.");
            p.setDeSuggestion("Sie können die Bestellung unter \"Mein Konto>Mein Bestellung>Unbezahlt\" anzeigen oder auf \"Bestellung anzeigen\" klicken. Sie können auch auf \"Erneut bezahlen\" klicken, um die Zahlung zurückzuzahlen.");
            return p;
        }
    },
    SYSTEM_ERROR {
        @Override
        public PaymentExceptionInfo getEnum() {
            PaymentExceptionInfo p = new PaymentExceptionInfo();
            p.setCode(10001);
            p.setCnMsg("因未知原因，该笔订单未完成交易。");
            p.setCnSuggestion("1.请检查一下，您的支付账号是否已经扣款；若没有扣款，请在几分钟后尝试再次付款。\n" +
                    "2.若您的支付账号已经扣款，但订单显示未付款；请及时联系平台进行核实确认。");
            p.setEnMsg("The order has not completed the transaction due to unknown reasons.");
            p.setEnSuggestion("1. Please kindly check if the money has been taken out from your payment account.If no, pls kindly try again a few minutes later, thanks.\n" +
                    "2.Please kindly contact us if the order still  pending payment after the money has been taken out.");
            p.setEsMsg("el pedido no ha completado la transacción por motivos");
            p.setEsSuggestion("por favor ,vertifique si el dinero se ha retirado de su cuenta de pago ,si no, porfavor vuelva a intentarlo unos minutos más tarde gracias");
            p.setFrMsg("La commande n'a pas terminé la transaction pour des raisons inconnues.");
            p.setFrSuggestion("1. Veuillez vérifier si l'argent a été retiré de votre compte de paiement. Si non, veuillez réessayer quelques minutes plus tard, merci.\n" +
                    "2.Veuillez nous contacter si la commande est toujours en attente de paiement après le retrait de l'argent.");
            p.setDeMsg("Aus unbekannten Gründen wurde die Bestellung nicht abgeschlossen.");
            p.setDeSuggestion("1. Bitte überprüfen Sie, ob das Geld von Ihrem Zahlungskonto abgebucht wurde. Wenn nein, versuchen Sie es bitte einige Minuten später erneut, danke.\n" +
                    "2.Bitte kontaktieren Sie uns, wenn die Bestellung nach der Auszahlung des Geldes noch aussteht.");
            return p;
        }
    },
    INSUFFICIENT_BALANCE {
        @Override
        public PaymentExceptionInfo getEnum() {
            PaymentExceptionInfo p = new PaymentExceptionInfo();
            p.setCode(10002);
            p.setCnMsg("支付失败，请确认卡上有足够的钱或使用其他卡。");
            p.setCnSuggestion("你可以在“账号＞订单＞未付款”中查看订单，或点击“View Order”。也可以点击\"Pay now again\"重新支付。");
            p.setEnMsg("Payment failed, and please kindly make sure that there is sufficient balance or use another card to pay again.");
            p.setEnSuggestion("You can enter “My Account>My Orders>Pendingpayment” or click \"View Order\" to check out your order. You can also click \"Pay again \" to re-pay.");
            p.setEsMsg("pago fallido, porfavor ,asegurarse que su cuenta queda saldo suficiente o usa otra tarjeta a pagar de nuevo");
            p.setEsSuggestion("puedes entrar¨mi cuenta>mis pedidos>pago pediente¨o click ¨ver el pedido¨a chequear tu pedido.también puedes click ¨paga otra vez¨ a pagar de nuevo.");
            p.setFrMsg("Le paiement a échoué, veuillez vous assurer que le solde est suffisant ou utilisez une autre carte pour payer à nouveau.");
            p.setFrSuggestion("Vous pouvez entrer \"Mon compte>Mes commandes>Paiement en attente\" ou cliquer sur \"Voir la commande\" pour vérifier votre commande. Vous pouvez également cliquer sur « Payer à nouveau » pour re-payer.");
            p.setDeMsg("Die Zahlung ist fehlgeschlagen. Bitte stellen Sie sicher, dass ein ausreichendes Guthaben vorhanden ist, oder verwenden Sie eine andere Karte, um erneut zu bezahlen.");
            p.setDeSuggestion("Sie können die Bestellung unter \"Mein Konto> Mein Bestellung>Unbezahlt\" anzeigen oder auf \"Bestellung anzeigen\" klicken. Sie können auch auf \"Erneut bezahlen\" klicken, um die Zahlung zurückzuzahlen.");
            return p;
        }
    },
    DUPLICATE_ORDER {
        @Override
        public PaymentExceptionInfo getEnum() {
            PaymentExceptionInfo p = new PaymentExceptionInfo();
            p.setCode(10003);
            p.setCnMsg("该订单已经完成交易，请勿重复付款。");
            p.setCnSuggestion("你可以在“账号＞订单＞未付款”中查看订单，或点击“View Order”。");
            p.setEnMsg("You have paid for the order, so do not pay twice. ");
            p.setEnSuggestion("You can enter “My Account>My Orders>Pendingpayment” or click \"View Order\" to check out your order. ");
            p.setEsMsg("el pedido se ha pagado,no lo paga otra vez");
            p.setEsSuggestion("Puede ingresar a \"Mi cuenta> Mis pedidos> Pago pendiente\" o hacer clic en \"Ver pedido\" para verificar su pedido.");
            p.setFrMsg("Vous avez payé la commande, alors ne payez pas deux fois.");
            p.setFrSuggestion("Vous pouvez entrer \"Mon compte>Mes commandes>Paiement en attente\" ou cliquer sur \"Voir la commande\" pour vérifier votre commande.");
            p.setDeMsg("Sie haben die Bestellung bezahlt, also zahlen Sie nicht zweimal.");
            p.setDeSuggestion("Sie können die Bestellung unter \"Nein Konto>Mein Bestellung>Unbezahlt\" anzeigen oder auf \"Bestellung anzeigen\" klicken.");
            return p;
        }
    },
    TRANSACTION_DO_NOT_HONOUR {
        @Override
        public PaymentExceptionInfo getEnum() {
            PaymentExceptionInfo p = new PaymentExceptionInfo();
            p.setCode(10004);
            p.setCnMsg("发行卡不信任");
            p.setCnSuggestion("1. 再次尝试付款，您的发卡机构可能会接受您的付款。\n" +
                    "2. 拨打卡背面的800号码，要求发卡机构放款，然后再付款。\n" +
                    "3. 换一张卡支付");
            p.setEnMsg("Transaction Do Not Honour");
            p.setEnSuggestion("1. Try to Pay again, your card issuer may accept your payment.\n" +
                    "2. Call the 800 number on the back of the card to ask your card issuer to release the money, then pay again.\n" +
                    "3. Change another card to pay");
            p.setEsMsg("Transacción no honrar");
            p.setEsSuggestion("1. Intente pagar de nuevo, el emisor de su tarjeta puede aceptar su pago.\n" +
                    "2. Llame al número 800 que se encuentra en el reverso de la tarjeta para pedirle al emisor de su tarjeta que libere el dinero y luego pague nuevamente.\n" +
                    "3. Cambie otra tarjeta para pagar");
            p.setFrMsg("La transaction n'honore pas");
            p.setFrSuggestion("1. Essayez de payer à nouveau, l'émetteur de votre carte peut accepter votre paiement.\n" +
                    "2. Appelez le numéro 800 au dos de la carte pour demander à l'émetteur de votre carte de libérer l'argent, puis payez à nouveau.\n" +
                    "3. Changer une autre carte pour payer");
            p.setDeMsg("Transaktion nicht ehren");
            p.setDeSuggestion("1. Versuchen Sie erneut zu bezahlen. Ihr Kartenaussteller kann Ihre Zahlung akzeptieren.\n" +
                    "2. Rufen Sie die 800-Nummer auf der Rückseite der Karte an, um Ihren Kartenaussteller zu bitten, das Geld freizugeben, und zahlen Sie dann erneut.\n" +
                    "3. Ändern Sie eine andere Karte zum Bezahlen");
            return p;
        }
    },
    TRANSACTION_NOT_PERMITTED_ON_CARD {
        @Override
        public PaymentExceptionInfo getEnum() {
            PaymentExceptionInfo p = new PaymentExceptionInfo();
            p.setCode(10005);
            p.setCnMsg("卡交易受限");
            p.setCnSuggestion("1. 再次尝试付款，您的发卡机构可能会接受您的付款。\n" +
                    "2. 拨打卡背面的800号码，要求发卡机构放款，然后再付款。\n" +
                    "3. 换一张卡支付");
            p.setEnMsg("Transaction Not Permitted On Card");
            p.setEnSuggestion("1. Try to Pay again, your card issuer may accept your payment.\n" +
                    "2. Call the 800 number on the back of the card to ask your card issuer to release the money, then pay again.\n" +
                    "3. Change another card to pay");
            p.setEsMsg("Transacción no permitida en la tarjeta");
            p.setEsSuggestion("1. Intente pagar de nuevo, el emisor de su tarjeta puede aceptar su pago.\n" +
                    "2. Llame al número 800 que se encuentra en el reverso de la tarjeta para pedirle al emisor de su tarjeta que libere el dinero y luego pague nuevamente.\n" +
                    "3. Cambie otra tarjeta para pagar");
            p.setFrMsg("Transaction non autorisée sur la carte");
            p.setFrSuggestion("1. Essayez de payer à nouveau, l'émetteur de votre carte peut accepter votre paiement.\n" +
                    "2. Appelez le numéro 800 au dos de la carte pour demander à l'émetteur de votre carte de libérer l'argent, puis payez à nouveau.\n" +
                    "3. Changer une autre carte pour payer");
            p.setDeMsg("Transaktion auf Karte nicht zulässig");
            p.setDeSuggestion("1. Versuchen Sie erneut zu bezahlen. Ihr Kartenaussteller kann Ihre Zahlung akzeptieren.\n" +
                    "2. Rufen Sie die 800-Nummer auf der Rückseite der Karte an, um Ihren Kartenaussteller zu bitten, das Geld freizugeben, und zahlen Sie dann erneut.\n" +
                    "3. Ändern Sie eine andere Karte zum Bezahlen");
            return p;
        }
    },
    RESTRICTED_CARD_OR_RETAIN_CARD {
        @Override
        public PaymentExceptionInfo getEnum() {
            PaymentExceptionInfo p = new PaymentExceptionInfo();
            p.setCode(10006);
            p.setCnMsg("限制卡/卡保留");
            p.setCnSuggestion("1. 再次尝试付款，您的发卡机构可能会接受您的付款。\n" +
                    "2. 拨打卡背面的800号码，要求发卡机构放款，然后再付款。\n" +
                    "3. 换一张卡支付");
            p.setEnMsg("Restricted Card/Retain Card");
            p.setEnSuggestion("1. Try to Pay again, your card issuer may accept your payment.\n" +
                    "2. Call the 800 number on the back of the card to ask your card issuer to release the money, then pay again.\n" +
                    "3. Change another card to pay");
            p.setEsMsg("Tarjeta restringida / Tarjeta de retención");
            p.setEsSuggestion("1. Intente pagar de nuevo, el emisor de su tarjeta puede aceptar su pago.\n" +
                    "2. Llame al número 800 que se encuentra en el reverso de la tarjeta para pedirle al emisor de su tarjeta que libere el dinero y luego pague nuevamente.\n" +
                    "3. Cambie otra tarjeta para pagar");
            p.setFrMsg("Carte restreinte/Carte de conservation");
            p.setFrSuggestion("1. Essayez de payer à nouveau, l'émetteur de votre carte peut accepter votre paiement.\n" +
                    "2. Appelez le numéro 800 au dos de la carte pour demander à l'émetteur de votre carte de libérer l'argent, puis payez à nouveau.\n" +
                    "3. Changer une autre carte pour payer");
            p.setDeMsg("Eingeschränkte Karte/Retain Card");
            p.setDeSuggestion("1. Versuchen Sie erneut zu bezahlen. Ihr Kartenaussteller kann Ihre Zahlung akzeptieren.\n" +
                    "2. Rufen Sie die 800-Nummer auf der Rückseite der Karte an, um Ihren Kartenaussteller zu bitten, das Geld freizugeben, und zahlen Sie dann erneut.\n" +
                    "3. Ändern Sie eine andere Karte zum Bezahlen");
            return p;
        }
    },
    EXCEEDS_PAYMENT_LIMIT {
        @Override
        public PaymentExceptionInfo getEnum() {
            PaymentExceptionInfo p = new PaymentExceptionInfo();
            p.setCode(10007);
            p.setCnMsg("交易金额受限");
            p.setCnSuggestion("1. 请联系您的发卡机构解决问题\n" +
                    "2. 换一张卡支付");
            p.setEnMsg("Exceed Payment Limit");
            p.setEnSuggestion("1. Please contact your card issuer to fix the problem\n" +
                    "2. Change another card to pay");
            p.setEsMsg("Exceder el límite de pago");
            p.setEsSuggestion("1. Comuníquese con el emisor de su tarjeta para solucionar el problema.\n" +
                    "2. Cambie otra tarjeta para pagar");
            p.setFrMsg("Dépasser la limite de paiement");
            p.setFrSuggestion("1. Veuillez contacter l'émetteur de votre carte pour résoudre le problème\n" +
                    "2. Changer une autre carte pour payer");
            p.setDeMsg("Zahlungslimit überschreiten");
            p.setDeSuggestion("1. Bitte wenden Sie sich an Ihren Kartenaussteller, um das Problem zu beheben\n" +
                    "2. Ändern Sie eine andere Karte, um zu bezahlen");
            return p;
        }
    },
    NOT_LOGGED_IN {
        @Override
        public PaymentExceptionInfo getEnum() {
            PaymentExceptionInfo p = new PaymentExceptionInfo();
            p.setCode(10008);
            p.setCnMsg("已经注册账号了？");
            p.setCnSuggestion("登录");
            p.setEnMsg("Already have a registered account, right?");
            p.setEnSuggestion("Login in");
            p.setEsMsg("Ya tienes una cuenta registrada, ¿verdad?");
            p.setEsSuggestion("Iniciar sesión");
            p.setFrMsg("Vous avez déjà un compte enregistré ?");
            p.setFrSuggestion("Se connecter");
            p.setDeMsg("Sie haben bereits ein registriertes Konto, oder?");
            p.setDeSuggestion("Einloggen");
            return p;
        }
    },
    ;

    public abstract PaymentExceptionInfo getEnum();

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class PaymentExceptionInfo {

        private Integer code;
        private String cnMsg;
        private String cnSuggestion;
        private String enMsg;
        private String enSuggestion;
        private String esMsg;
        private String esSuggestion;
        private String frMsg;
        private String frSuggestion;
        private String deMsg;
        private String deSuggestion;

        public PaymentExceptionSuggestion getCnMsgAndSuggestion() {
            return new PaymentExceptionSuggestion(this.getCode(), this.getCnMsg(), this.getCnSuggestion());
        }

        public PaymentExceptionSuggestion getEnMsgAndSuggestion() {
            return new PaymentExceptionSuggestion(this.getCode(), this.getEnMsg(), this.getEnSuggestion());
        }

        public PaymentExceptionSuggestion getEsMsgAndSuggestion() {
            return new PaymentExceptionSuggestion(this.getCode(), this.getEsMsg(), this.getEsSuggestion());
        }

        public PaymentExceptionSuggestion getFrMsgAndSuggestion() {
            return new PaymentExceptionSuggestion(this.getCode(), this.getFrMsg(), this.getFrSuggestion());
        }

        public PaymentExceptionSuggestion getDeMsgAndSuggestion() {
            return new PaymentExceptionSuggestion(this.getCode(), this.getDeMsg(), this.getDeSuggestion());
        }

        @ToString
        @AllArgsConstructor
        private class PaymentExceptionSuggestion {
            private Integer code;
            private String msg;
            private String suggestion;
        }
    }
}