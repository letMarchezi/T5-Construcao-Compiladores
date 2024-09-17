package br.ufscar.dc.compiladores.alguma.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.AlgumaGrammar;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.EntradaTabelaDeSimbolos;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.TipoEntrada;

import org.apache.commons.lang3.StringUtils;
public class GeradorAlgumaC extends AlgumaGrammarBaseVisitor<Void> {
    private StringBuilder codigoC = new StringBuilder();
    TabelaDeSimbolos tab = new TabelaDeSimbolos();
    private Map<String, EntradaTabelaDeSimbolos> tabela = new HashMap<>();

    int tab_spaces = 1;
    // TabelaDeSimbolos tabelaEscopos;
    // static Escopos escoposAninhados = new Escopos();
    public void printTabs(){
        for (int i=0; i< tab_spaces; i++)
            codigoC.append("\t");
    }

    public String getCodigoC() {
        return codigoC.toString();
    }

    // Inicia o programa
    @Override
    public Void visitPrograma(AlgumaGrammarParser.ProgramaContext ctx) {
        //StringBuilder inicioC = new StringBuilder();
        codigoC.append("#include <stdio.h>\n" + "#include <stdlib.h>\n\n" + "int main() {\n");
        // Adiciona estilização do HTML
    
        Void result = super.visitPrograma(ctx);
        
        codigoC.append("\treturn 0;\n}\n");

        return result;
        // // Após visitar todos os bloco, chama o agendador
        // planner.planejarEstudos(false);

        // // Concatena o html da tabela do cronograma de estudos com as informações gerais
        // inicio_html.append(planner.mostrarAgenda());
        // html = inicio_html.append(html);
        // html = html.append("\n\t</body>\n</html>");
    }

    public String converterTipo(String tipo_grammar){
        String tipo_c = null;
        switch (tipo_grammar){
            case "inteiro":
                tipo_c = "int";
                break;
            case "real":
                tipo_c = "float";
                break;
            case "literal":
                tipo_c = "char";
            default:
        }

        return tipo_c;
    }

    class Result {
        AlgumaGrammar tipo;
        String ident;
        String value;
        String operacao;
    
        public Result(AlgumaGrammar tipo, String ident, String value, String oper) {
            this.tipo = tipo;
            this.ident = ident;
            this.value = value;
            this.operacao = oper;
        }
    }

    public List<Result> determinarTipoExpressao(AlgumaGrammarParser.ExpressaoContext expr) {
        List<Result> variaveis = new ArrayList<>();
        if (expr.termo_logico() != null && expr.termo_logico(0).fator_logico() != null) {
            AlgumaGrammarParser.Fator_logicoContext fatorLogico = expr.termo_logico(0).fator_logico(0);
            
            if (fatorLogico.parcela_logica() != null) {
                AlgumaGrammarParser.Parcela_logicaContext parcelaLogica = fatorLogico.parcela_logica();
                if (parcelaLogica.exp_relacional() != null) {
                    AlgumaGrammarParser.Exp_relacionalContext expRelacional = parcelaLogica.exp_relacional();
                    String op_rel = null;
                    if (expRelacional.op_relacional() != null){
                        op_rel = expRelacional.op_relacional().getText();
                    }
                        if (expRelacional.exp_aritmetica() != null) {
                            List<AlgumaGrammarParser.Exp_aritmeticaContext> expAritmetica = expRelacional.exp_aritmetica();
                        for (int k=0; k<expAritmetica.size(); k++){ 
                            if (expAritmetica.get(k).termo() != null) {
                                List<AlgumaGrammarParser.TermoContext> termos = expAritmetica.get(k).termo();
                                
                                for (int i=0;i<termos.size();i++){
                                    var termo = termos.get(i);
                                    if (termo.fator() != null) {
                                        List<AlgumaGrammarParser.FatorContext> fatores = termo.fator();
                                        
                                        for (int j=0; j<fatores.size();j++){
                                            var fator = fatores.get(j); 
                                            if (fator.parcela() != null) {
                                                AlgumaGrammarParser.ParcelaContext parcela = fator.parcela(0);
                                                Result resultado_parcela = visitFatorIdent(parcela);
                                                if (expAritmetica.get(k).op1(j)!=null && i==0){
                                                    String tipo_op = expAritmetica.get(k).op1(j).getText();
                                                    resultado_parcela.operacao = tipo_op;
                                                }
                                                if (termo.op2(j)!=null && i==1){
                                                    String tipo_op = termo.op2(j).getText();
                                                    resultado_parcela.operacao = tipo_op;
                                                }
                                                if (fator.op3(j)!=null && i==2){
                                                    String tipo_op = fator.op3(j).getText();
                                                    resultado_parcela.operacao = tipo_op;
                                                }
                                                if(op_rel != null){
                                                    resultado_parcela.operacao = op_rel;
                                                    op_rel = null;
                                                }
                                                variaveis.add(resultado_parcela);
                                            }
                                        }
                                    }
                                }
                                
                                if (termos.size() > 1) {
                                    
                                    AlgumaGrammar finalTipo = verificarTipo(variaveis);
                                    for (int i=0; i<termos.size(); i++){
                                        variaveis.get(i).tipo = finalTipo;
                                        //System.out.println(variaveis.get(i).tipo);
                                    }
                                }
                                
                            }
                        }
                        return variaveis;
                    }
                }
            }
        }
        variaveis.add(new Result(AlgumaGrammar.INVALIDO, null, null, null));
        return variaveis;   
    }

     // Verificação do tipo de variáveis de parcela
     public static AlgumaGrammar verificarTipo(List<Result> variaveis) {
        boolean temReal = false;
        boolean temInt = false;
        
        for (Result var : variaveis) {
            if (var.tipo == AlgumaGrammar.REAL) {
                temReal = true;
            } else if (var.tipo == AlgumaGrammar.INTEIRO) {
                temInt = true;
            }
        }
        
        if (temReal) {
            return AlgumaGrammar.REAL; // Caso algum operando seja real, o retorno é real
        } else if (temInt) {
            return AlgumaGrammar.INTEIRO;
        }
        
        return AlgumaGrammar.INVALIDO;
    }

    
    public Result visitFatorIdent(AlgumaGrammarParser.ParcelaContext parcela){
        if (parcela.parcela_unario() != null && parcela.parcela_unario().identificador() != null) {
            String nomeIdentificador = parcela.parcela_unario().identificador().getText();
            EntradaTabelaDeSimbolos entrada = tabela.get(nomeIdentificador);
            
            if (entrada != null) {
                return new Result(entrada.tipo, nomeIdentificador, null, null); // Return the identifier's type
            }
            else if(parcela.parcela_unario().NUM_INT()!=null){
                return new Result(AlgumaGrammar.INTEIRO, null, parcela.parcela_unario().NUM_INT().getText(), null); // Return the identifier's type
            }else if(parcela.parcela_unario().NUM_REAL()!=null){
                return new Result(AlgumaGrammar.REAL, null, parcela.parcela_unario().NUM_REAL().getText(), null); // Return the identifier's type
            }
            else {
                return new Result(AlgumaGrammar.INVALIDO, null, null, null);
            }
            
        }

        if (parcela.parcela_unario() != null) {
            if (parcela.parcela_unario().NUM_INT() != null) {
                return new Result(AlgumaGrammar.INTEIRO, null, parcela.parcela_unario().NUM_INT().getText(), null);
            } else if (parcela.parcela_unario().NUM_REAL() != null) {
                return new Result(AlgumaGrammar.REAL, null,  parcela.parcela_unario().NUM_REAL().getText(), null);
            }
        }
        
        if (parcela.parcela_nao_unario() != null && parcela.parcela_nao_unario().CADEIA() != null) {
            return new Result(AlgumaGrammar.LITERAL, null, parcela.parcela_nao_unario().CADEIA().getText(), null);
        }
        return new Result(AlgumaGrammar.INVALIDO, null, null, null);
    }

    


    // Registra os dias da semana e o período de tempo disponível para estudos
    // Insere as atividades como tempo ocupado 
    
    @Override
    public Void visitDeclaracao_local(AlgumaGrammarParser.Declaracao_localContext ctx) {
        String decl = "";
        if (ctx.variavel() != null){
            String tipo = ctx.variavel().tipo().getText();
            String tipo_c = converterTipo(tipo);
            
            printTabs();
            codigoC.append(tipo_c+" ");

            for (int i=0; i<ctx.variavel().identificador().size(); i++){
                //var ident: ctx.variavel().identificador()
                var ident = ctx.variavel().identificador(i);
                String dimension = "";
                if (tipo_c == "char"){
                    dimension = "[80]";
                }
                if(i>0)
                    decl = decl + ", ";
                decl = decl + ident.getText()+dimension;
                
                //codigoC.append(ident.getText()+""+dimension+";\n");
                tabela.put(ident.getText(), tab.new EntradaTabelaDeSimbolos(ident.getText(), AlgumaSemantico.determinarTipoAlgumaGrammar(tipo), TipoEntrada.VARIAVEL, false, -1));
            } 
            
            codigoC.append(decl + ";\n");

        }

        return super.visitDeclaracao_local(ctx);
    }

    @Override
    public Void visitCmdSe(AlgumaGrammarParser.CmdSeContext ctx) {
        printTabs();
        tab_spaces++; 
    
        // Imprime 'if'
        codigoC.append("if (");

        //var exp_rel = ctx.expressao().termo_logico(0).fator_logico(0).parcela_logica().exp_relacional();
        //for (int i=0; i<exp_rel.exp_aritmetica().size(); i++){
        List<Result> condicao = determinarTipoExpressao(ctx.expressao());
        //for (var termo_expr_relacional: ctx.expressao().termo_logico(0).fator_logico(0).parcela_logica().exp_relacional().children)
        // Visita a expressão condicional (exemplo: '4 > 3')
        for (int i=0; i < condicao.size(); i++){    
            var expressao_var = condicao.get(i);

            if (expressao_var.ident != null) {
                codigoC.append(expressao_var.ident);  // Caso seja identificador
            } else if (expressao_var.value != null) {
                codigoC.append(expressao_var.value);  // Caso seja literal ou numérico
                if (expressao_var.operacao != null){
                    if (expressao_var.operacao.equals("=")){
                        expressao_var.operacao = "==";
                    }
                    codigoC.append(" "+expressao_var.operacao+" ");
                }
            }
        }
        // Fecha a condição
        codigoC.append(") {\n");

        // int entaoCmdCount = ctx.getChildCount() > ctx.cmd().size() ? ctx.cmd().size() - 1 : ctx.cmd().size();
        // for (int i = 0; i < entaoCmdCount; i++) {
        //     visit(ctx.cmd(i));  // Visita os comandos do bloco 'entao'
        // }

        for (int i = 0; i < ctx.cmd().size(); i++) {
            if (ctx.getChild(i).getText().equals("fim_se")) {
                break;
            }
            visit(ctx.cmd(i));  // Visit each command in the 'entao' block
        }
        // Fecha o bloco
        tab_spaces--;
        printTabs();
        codigoC.append("}\n");
        
        // Imprime o bloco senao
        if (ctx.getText().contains("senao")) {
            
            printTabs();
            codigoC.append("else {\n");
            tab_spaces++; 
            
            // Visita os comandos dentro do bloco 'senao'
            for (int i = ctx.cmd().size() / 2; i < ctx.cmd().size(); i++) {
                visit(ctx.cmd(i)); 
            }

            tab_spaces--;
            printTabs();
            codigoC.append("}\n");
        }
        return null;
    }


    @Override
    public Void visitCmdAtribuicao(AlgumaGrammarParser.CmdAtribuicaoContext ctx) {
        printTabs();
        // Operador da esquerda
        String lhs = ctx.identificador().getText();
    
        // Expressão da direita
        StringBuilder rhs = new StringBuilder();
        
        // Visita a expressão da direita
        List<Result> variaveis = determinarTipoExpressao(ctx.expressao());
    
        for (int i = 0; i < variaveis.size(); i++) {
            Result var = variaveis.get(i);
            if (var.ident != null) {
                rhs.append(var.ident);  // Adiciona o identificador
            } else if (var.value != null) {
                rhs.append(var.value);  // Adiciona o valor
            }
    
            // Adiciona o operador aritmético
            if (i < variaveis.size() - 1) {
                rhs.append(" + ");  
            }
        }
    
        // Generate the C assignment statement
        codigoC.append(lhs + " = " + rhs.toString() + ";\n");
    
        return null;
    }
    @Override
    public Void visitCmdLeia(AlgumaGrammarParser.CmdLeiaContext ctx) {
        printTabs();
        for (var ident: ctx.identificador()){
            String tipo_escrita = null;

            AlgumaGrammar tipo = tabela.get(ident.getText()).tipo;
            if(tipo==AlgumaGrammar.INTEIRO){
                tipo_escrita = "%d";
                codigoC.append("scanf(\""+tipo_escrita+"\",&"+ident.getText()+");\n");
            }
            else if(tipo==AlgumaGrammar.REAL){
                tipo_escrita = "%f";
                codigoC.append("scanf(\""+tipo_escrita+"\",&"+ident.getText()+");\n");
            }
            else if(tipo==AlgumaGrammar.LITERAL){
                codigoC.append("gets("+ident.getText()+");\n");
            }   
            
        }

        return super.visitCmdLeia(ctx);
    }
    @Override
    public Void visitCmdEscreva(AlgumaGrammarParser.CmdEscrevaContext ctx) {
        printTabs();
        StringBuilder nome_params = new StringBuilder();
        StringBuilder literals = new StringBuilder("\"");
        boolean teve_cadeia = false;
        int count_params=0;
        for (int i=0; i < ctx.expressao().size(); i++){
            var exp = ctx.expressao(i);
            //var variaveis_dados = determinarTipoExpressao(exp);
            var variaveis = determinarTipoExpressao(exp);
            for (int j=0; j<variaveis.size();j++){
                
                var variavel_dados = variaveis.get(j);

                if (variavel_dados.tipo == AlgumaGrammar.INTEIRO){
                    count_params++;
                    if(teve_cadeia){
                        nome_params.append(",");
                        teve_cadeia = false;
                    }
                    // não há operação
                    if(variavel_dados.operacao == null){
                        literals.append("%d");
                        nome_params.append(variavel_dados.ident);
                    }else{
                        nome_params.append(variavel_dados.operacao);
                        teve_cadeia = false;
                    }
                }else if(variavel_dados.tipo==AlgumaGrammar.REAL){
                    count_params++;
                    if(teve_cadeia){
                        nome_params.append(",");
                        teve_cadeia = false;
                    }

                    if(variavel_dados.operacao == null){
                        literals.append("%f");
                        nome_params.append(variavel_dados.ident);
                    }else{
                        nome_params.append(variavel_dados.ident+variavel_dados.operacao);
                    }
                }else if (variavel_dados.tipo==AlgumaGrammar.LITERAL && variavel_dados.value == null){
                    count_params++;
                    if(teve_cadeia){
                        nome_params.append(",");
                        teve_cadeia = false;
                    }
                    if(variavel_dados.operacao == null){
                        literals.append("%s");
                        nome_params.append(variavel_dados.ident);
                    }else{
                        nome_params.append(variavel_dados.ident+variavel_dados.operacao);
                    }
                }else if (variavel_dados.tipo==AlgumaGrammar.LITERAL && variavel_dados.value != null){
                    String interm_string = variavel_dados.value.split("\"")[1];
                    literals.append(interm_string);
                    teve_cadeia = true;
                    
                }
            }   
        }
        literals.append("\"");
        if(!(nome_params.toString().startsWith(","))&&(count_params>0)){
            literals.append(",");
        }

        codigoC.append(("printf("+literals.toString()+nome_params.toString()+");").trim()+"\n");
        //codigoC.append(("printf("+literals.toString()+(count_params>0 ? "," : "")+nome_params.toString()+");").trim()+"\n");
        
        //codigoC.append("printf(\""+tipoLer+"\","+variavel.ident+");\n");

        return super.visitCmdEscreva(ctx);
    }
    
}
