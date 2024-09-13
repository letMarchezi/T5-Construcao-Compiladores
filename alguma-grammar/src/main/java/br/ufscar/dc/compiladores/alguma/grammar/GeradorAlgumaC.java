package br.ufscar.dc.compiladores.alguma.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.AlgumaGrammar;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.EntradaTabelaDeSimbolos;
import br.ufscar.dc.compiladores.alguma.grammar.TabelaDeSimbolos.TipoEntrada;

import br.ufscar.dc.compiladores.alguma.grammar.AlgumaSemantico;
import br.ufscar.dc.compiladores.alguma.grammar.AlgumaGrammarParser.Parcela_unarioContext;

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

    public String getHtml() {
        return codigoC.toString();
    }

    // Inicia o programa
    @Override
    public Void visitPrograma(AlgumaGrammarParser.ProgramaContext ctx) {
        //StringBuilder inicioC = new StringBuilder();
        codigoC.append("#include <stdio.h>\n" + "#include <stdlib.h>\n\n" + "int main() {\n");
        // Adiciona estilização do HTML
    
        Void result = super.visitPrograma(ctx);
        
        codigoC.append("\treturn 0;\n}");

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
    
        public Result(AlgumaGrammar value1, String value2, String value3) {
            this.tipo = value1;
            this.ident = value2;
            this.value = value3;
        }
    }

    public List<Result> determinarTipoExpressao(AlgumaGrammarParser.ExpressaoContext expr) {
        String nomeIdentificador = null; 
        List<Result> variaveis = new ArrayList<>();
        if (expr.termo_logico() != null && expr.termo_logico(0).fator_logico() != null) {
            AlgumaGrammarParser.Fator_logicoContext fatorLogico = expr.termo_logico(0).fator_logico(0);
            
            if (fatorLogico.parcela_logica() != null) {
                AlgumaGrammarParser.Parcela_logicaContext parcelaLogica = fatorLogico.parcela_logica();
                if (parcelaLogica.exp_relacional() != null) {
                    AlgumaGrammarParser.Exp_relacionalContext expRelacional = parcelaLogica.exp_relacional();
                    
                    if (expRelacional.exp_aritmetica() != null) {
                        AlgumaGrammarParser.Exp_aritmeticaContext expAritmetica = expRelacional.exp_aritmetica(0);
                        
                        if (expAritmetica.termo() != null) {
                            List<AlgumaGrammarParser.TermoContext> termos = expAritmetica.termo();
                            
                            for (var termo: termos){
                                
                                if (termo.fator() != null) {
                                    List<AlgumaGrammarParser.FatorContext> fatores = termo.fator();
                                    
                                    for (var fator: termo.fator()){ 
                                        if (fator.parcela() != null) {
                                            AlgumaGrammarParser.ParcelaContext parcela = fator.parcela(0);
                                            Result resultado_parcela = visitFatorIdent(parcela);
                                            variaveis.add(resultado_parcela);
                                        }
                                    }

                                }
                                if (termos.size() > 1) {
                                    
                                    AlgumaGrammar finalTipo = verificarTipo(variaveis);
                                    return List.of(new Result(finalTipo, null, null)); // Returning final type
                                }
                            }
                            return variaveis;
                        }
                    }
                }
            }
        }
        variaveis.add(new Result(AlgumaGrammar.INVALIDO, null, null));
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
                return new Result(entrada.tipo, nomeIdentificador, null); // Return the identifier's type
            } else {

                return new Result(AlgumaGrammar.INVALIDO, null, null);
            }
        }
        

        if (parcela.parcela_unario() != null) {
            if (parcela.parcela_unario().NUM_INT() != null) {
                return new Result(AlgumaGrammar.INTEIRO, null, null);
            } else if (parcela.parcela_unario().NUM_REAL() != null) {
                return new Result(AlgumaGrammar.REAL, null, null);
            }
        }
        
        if (parcela.parcela_nao_unario() != null && parcela.parcela_nao_unario().CADEIA() != null) {
            return new Result(AlgumaGrammar.LITERAL, null, parcela.parcela_nao_unario().CADEIA().getText());
        }
        return new Result(AlgumaGrammar.INVALIDO, null, null);
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


            List<String> lista_ident = new ArrayList<>();
            for (int i=0; i<ctx.variavel().identificador().size(); i++){
                //var ident: ctx.variavel().identificador()
                var ident = ctx.variavel().identificador(i);
                String dimension = "";
                if (tipo_c == "char"){
                    dimension = "[80]";
                }
                if(i>0)
                    decl = decl + ", ";
                decl = decl + ident.getText();
                
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
        System.out.println("se " + ctx.expressao().getText() + " entao");
        tab_spaces++; 
        visitChildren(ctx);
        tab_spaces--;  
        printTabs();
        System.out.println("fim_se");
        return null;
    }

    @Override
    public Void visitCmdEnquanto(AlgumaGrammarParser.CmdEnquantoContext ctx) {
        printTabs();  
        System.out.println("enquanto " + ctx.expressao().getText() + " faca");
        tab_spaces++;  
        visitChildren(ctx);
        tab_spaces--;  
        printTabs();
        System.out.println("fim_enquanto");
        return null;
    }

    public Void visitCmdPara(AlgumaGrammarParser.CmdParaContext ctx) {
        printTabs();
        System.out.println("para " + ctx.IDENT().getText() + " <- " + ctx.exp_aritmetica(0).getText() + " ate " + ctx.exp_aritmetica(1).getText() + " faca");
        tab_spaces++;
        visitChildren(ctx);
        tab_spaces--;
        printTabs();
        System.out.println("fim_para");
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
                codigoC.append("scanf(\""+tipo_escrita+"\", &"+ident.getText()+");\n");
            }
            else if(tipo==AlgumaGrammar.REAL){
                tipo_escrita = "%f";
                codigoC.append("scanf(\""+tipo_escrita+"\", &"+ident.getText()+");\n");
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
        
        for (int i=0; i < ctx.expressao().size(); i++){
            var exp = ctx.expressao(i);
            //var variaveis_dados = determinarTipoExpressao(exp);
            var variaveis = determinarTipoExpressao(exp);
            for (int j=0; j<variaveis.size();j++){
                var variavel_dados = variaveis.get(j);
                if (variavel_dados.tipo == AlgumaGrammar.INTEIRO){
                    literals.append("%d");
                    nome_params.append(","+variavel_dados.ident);
                }else if(variavel_dados.tipo==AlgumaGrammar.REAL){
                    literals.append("%f");
                    //tipoLer = "%f";
                    nome_params.append(","+variavel_dados.ident);
                }else if (variavel_dados.tipo==AlgumaGrammar.LITERAL && variavel_dados.value == null){
                    //tipoLer = "%s";
                    literals.append("%s");
                    nome_params.append(","+variavel_dados.ident);
                }else if (variavel_dados.tipo==AlgumaGrammar.LITERAL && variavel_dados.value != null){
                    literals.append(StringUtils.strip(variavel_dados.value,"\""));
                }
            }   
        }
        literals.append("\"");
        codigoC.append("printf("+ literals.toString() + nome_params.toString() + ");\n");
        
        //codigoC.append("printf(\""+tipoLer+"\","+variavel.ident+");\n");

        return super.visitCmdEscreva(ctx);
    }

    @Override
    public Void visitDecl_local_global(AlgumaGrammarParser.Decl_local_globalContext ctx) {
        

        return super.visitDecl_local_global(ctx);
    }

    
    
}
